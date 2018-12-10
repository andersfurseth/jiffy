(ns jiffy.protocols.month-day
  (:refer-clojure :exclude [format ])
  (:require [clojure.spec.alpha :as s]))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/MonthDay.java
(defprotocol IMonthDay
  (get-month [this])
  (get-month-value [this])
  (get-day-of-month [this])
  (is-valid-year [this year])
  (with-month [this month])
  (with [this month])
  (with-day-of-month [this day-of-month])
  (format [this formatter])
  (at-year [this year])
  (is-after [this other])
  (is-before [this other]))

(s/def ::month-day #(satisfies? IMonthDay %))