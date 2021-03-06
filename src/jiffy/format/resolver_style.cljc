(ns jiffy.format.resolver-style
  (:require [clojure.spec.alpha :as s]
            [jiffy.dev.wip :refer [wip]]
            [jiffy.protocols.format.resolver-style :as resolver-style]
            [jiffy.specs :as j]))

(defrecord ResolverStyle [])

(s/def ::create-args ::j/wip)
(defn create [])
(s/def ::resolver-style (j/constructor-spec ResolverStyle create ::create-args))
(s/fdef create :args ::create-args :ret ::resolver-style)

(defmacro args [& x] `(s/tuple ::resolver-style ~@x))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/ResolverStyle.java
(defn values [] (wip ::values))
(s/fdef values :ret ::j/wip)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/ResolverStyle.java
(s/def ::value-of-args (args string?))
(defn value-of [value-of--unknown-param-name] (wip ::value-of))
(s/fdef value-of :args ::value-of-args :ret ::resolver-style)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/ResolverStyle.java
(def SMART ::SMART--not-implemented)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/ResolverStyle.java
(def STRICT ::STRICT--not-implemented)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/ResolverStyle.java
(def LENIENT ::LENIENT--not-implemented)