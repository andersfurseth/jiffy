(ns jiffy.parity-tests.support
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.string :as str]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test :refer [deftest is]]
            [jiffy.conversion :refer [jiffy->java] :as conversion]
            [jiffy.exception :refer [try*]]
            [jiffy.math :as math]
            [jiffy.specs :as j]))

(defn partition-between
  [pred? coll]
  (->> (map pred? coll (rest coll))
       (reductions not= true)
       (map list coll)
       (partition-by second)
       (map (partial map first))))

(defn kebab-case [s]
  (->> (str s)
       (partition-between #(and (Character/isLowerCase %1)
                                (Character/isUpperCase %2)))
       (map #(apply str %))
       (map str/lower-case)
       (str/join "-")))

(defn camel-case [s]
  (->> (str s)
       (partition-by #(= % \-))
       (map #(apply str %))
       (remove #(str/starts-with? % "-"))
       (map str/capitalize)
       (apply str)))

(defmacro trycatch [& exprs]
  `(try* ~@exprs (catch :default e# e#)))

(defmacro same? [call-jiffy-form call-java-form]
  `(conversion/same?
    (trycatch ~call-jiffy-form)
    (trycatch ~call-java-form)))

(defn invoke-java [f args {:keys [static?]}]
  (let [obj (when-not static? (first args))
        method-name (name f)
        method (->> (.getDeclaredMethods (Class/forName (namespace f)))
                    (filter #(-> % .getName (= method-name)))
                    (filter #(-> % .getParameterCount (= (count (if static? args (rest args))))))
                    first)]
    (when-not method
      (throw (ex-info (str "Unable to find Java method " method-name " on object " obj) {})))
    (try
      (if static?
        (.invoke method obj (into-array Object args))
        (.invoke method obj (into-array Object (rest args))))
      (catch java.lang.reflect.InvocationTargetException ite
        (throw (.getCause ite))))))

(def ns->class-anomalies
  {"jiffy.time-comparable" "java.lang.Comparable"})

(defn jiffy-ns->java-class [jiffy-ns-str]
  (or (ns->class-anomalies jiffy-ns-str)
      (let [idx (inc (str/last-index-of jiffy-ns-str "."))]
        (str (-> jiffy-ns-str
                 (subs 0 idx)
                 (str/replace #"jiffy" "java.time"))
             (-> jiffy-ns-str
                 (subs idx (count jiffy-ns-str))
                 camel-case)))))

(defn gen-test-name [f]
  (symbol (str (str/replace (namespace f) #"\." "-")
               "--"
               (name f))))

(defn get-spec [f]
  (keyword (str (namespace f))
           (str (kebab-case (name f)) "-args")))

(defn invoke-jiffy [f args]
  (apply f args))

(defn jiffy-fn->java-fn [s]
  (let [[first-char & rest] (camel-case s)]
    (str (str/lower-case (str first-char))
         (apply str rest))))

(defmacro gen-prop [jiffy-fn spec & [{:keys [static?]}]]
  `(prop/for-all
    [args# (s/gen ~spec)]
    (same? (invoke-jiffy ~jiffy-fn args#)
           (invoke-java '~(symbol (jiffy-ns->java-class (namespace jiffy-fn))
                                  (jiffy-fn->java-fn (name jiffy-fn)))
                        (map jiffy->java args#)
                        {:static? ~static?}))))

(def default-num-tests 1000)

(defmacro test-proto-fn [impl-ns proto-fn & [num-tests]]
  `(do
     (require '~(symbol (namespace proto-fn)))
     (require '~(symbol impl-ns))
     (defspec ~(gen-test-name proto-fn) ~(or num-tests default-num-tests)
       (gen-prop ~proto-fn
                 (get-spec '~(symbol (str impl-ns) (name proto-fn)))
                 {:static? false}))))

(defmacro test-static-fn [jiffy-fn & [num-tests]]
  `(do
     (require '~(symbol (namespace jiffy-fn)))
     (defspec ~(gen-test-name jiffy-fn) ~(or num-tests default-num-tests)
       (gen-prop ~jiffy-fn
                 (get-spec '~jiffy-fn)
                 {:static? true}))))

(defmacro test-fn! [jiffy-fn args-samples & [{:keys [static?]}]]
  `(do
     (let [results#
           (for [args# ~args-samples]
             (let [jiffy-result# (invoke-jiffy ~jiffy-fn args#)
                   java-args# (mapv jiffy->java args#)
                   result# {:failed/jiffy-args args#
                            :failed/jiffy-result jiffy-result#
                            :failed/java-args java-args#}
                   java-result# (try
                                  (invoke-java '~(symbol (jiffy-ns->java-class (namespace jiffy-fn))
                                                         (jiffy-fn->java-fn (name jiffy-fn)))
                                               java-args#
                                               {:static? ~static?})
                                  (catch Exception e#
                                    (throw (ex-info "Exception when invoking java.time"
                                                    result# e#))))]
               (when-not (same? jiffy-result# java-result#)
                 (assoc result# :failed/java-result java-result#))))]
       (or (first (remove nil? results#)) [(count results#) :success]))))

(defmacro test-proto-fn! [impl-ns proto-fn & [num-tests]]
  `(do
     (require '~(symbol (namespace proto-fn)))
     (require '~(symbol impl-ns))
     (test-fn! ~proto-fn
               (gen/sample (s/gen ~(get-spec (symbol (str impl-ns) (name proto-fn)))) ~(or num-tests 1000))
               {:static? false})))

(defmacro test-static-fn! [jiffy-fn & [num-tests]]
  `(do
     (require '~(symbol (namespace jiffy-fn)))
     (test-fn! ~jiffy-fn
               (gen/sample (s/gen ~(get-spec jiffy-fn)) ~(or num-tests 1000))
               {:static? true})))
