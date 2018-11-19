(ns jiffy.exception
  (:require [clojure.spec.alpha :as s]
            [jiffy.dev.wip :refer [wip]]))

(s/def ::catch-expr
  (s/cat :catch #(= 'catch %)
         :what (s/or :kv keyword? :symbol symbol?)
         :binding symbol?
         :exprs (s/* any?)))

(s/def ::try-exprs (s/+ (s/or :catch ::catch-expr
                              :body-expr any?)))

(defn cljs-env?
  "Given an &env from a macro, tells whether it is expanding into CLJS."
  [env]
  (boolean (:ns env)))

(defn gen-catch [env catch-exprs]
  (if-not (seq catch-exprs)
    []
    (let [caught-sym (gensym)]
      `(catch ~(if (cljs-env? env) :default 'Throwable) ~caught-sym
         (letfn [(~'matches? [t# thrown#]
                  (or (isa? t# (type thrown#))
                      (and (keyword? t#)
                           (or (some-> thrown# ex-data ::kind (isa? t#))
                               (= :default t#)))))]
           (cond
             ~@(->> (for [{:keys [what binding exprs]} catch-exprs]
                      `((~'matches? ~(second what) ~caught-sym)
                        (let [~binding ~caught-sym]
                          ~@exprs)))
                    (mapcat identity))
             :else
             (throw ~caught-sym)))))))

(defmacro try* [& exprs]
  (when-not (s/valid? ::try-exprs exprs)
    (throw (ex-info (s/explain-str ::try-exprs exprs) {})))
  (let [try-exprs (s/conform ::try-exprs exprs)
        try-body (->> try-exprs
                      (take-while (comp (partial = :body-expr) first))
                      (map second))
        catch-exprs (->> try-exprs
                         (drop-while (comp (partial = :body-expr) first))
                         (take-while (comp (partial = :catch) first))
                         (map second))]
    `(try
       ~@try-body
       ~(gen-catch &env catch-exprs))))

(defn ex
  ([kind message] (ex kind message {} nil))
  ([kind message data] (ex kind message data nil))
  ([kind message data cause] (ex-info message
                                      (assoc data ::kind kind)
                                      cause)))

(def DateTimeException ::DateTimeException)

(def UnsupportedTemporalTypeException ::UnsupportedTemporalTypeException)
(derive UnsupportedTemporalTypeException DateTimeException)

(def ZoneRulesException ::ZoneRulesException)
(derive ZoneRulesException DateTimeException)

(def DateTimeParseException ::DateTimeParseException)
(derive DateTimeParseException DateTimeException)

(def JavaArithmeticException ::JavaArithmeticException)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/DateTimeParseException.java#L125
(defn getParsedString [this] (wip ::getParsedString))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/DateTimeParseException.java#L134
(defn getErrorIndex [this] (wip ::getErrorIndex))