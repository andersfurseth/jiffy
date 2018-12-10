(ns jiffy.protocols.period
  (:require [clojure.spec.alpha :as s]))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/Period.java
(defprotocol IPeriod
  (get-years [this])
  (get-months [this])
  (get-days [this])
  (with-years [this years])
  (with-months [this months])
  (with-days [this days])
  (plus-years [this years-to-add])
  (plus-months [this months-to-add])
  (plus-days [this days-to-add])
  (minus-years [this years-to-subtract])
  (minus-months [this months-to-subtract])
  (minus-days [this days-to-subtract])
  (to-total-months [this]))

(s/def ::period #(satisfies? IPeriod %))