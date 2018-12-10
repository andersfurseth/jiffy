(ns jiffy.protocols.format.date-time-formatter-builder
  (:require [clojure.spec.alpha :as s]))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/format/DateTimeFormatterBuilder.java
(defprotocol IDateTimeFormatterBuilder
  (parse-case-sensitive [this])
  (parse-case-insensitive [this])
  (parse-strict [this])
  (parse-lenient [this])
  (parse-defaulting [this field value])
  (append-value [this field] [this field width] [this field min-width max-width sign-style])
  (append-value-reduced [this append-value-reduced--overloaded-param-1 append-value-reduced--overloaded-param-2 append-value-reduced--overloaded-param-3 append-value-reduced--overloaded-param-4])
  (append-fraction [this field min-width max-width decimal-point])
  (append-text [this field] [this append-text--overloaded-param-1 append-text--overloaded-param-2])
  (append-instant [this] [this fractional-digits])
  (append-offset-id [this])
  (append-offset [this pattern no-offset-text])
  (append-localized-offset [this style])
  (append-zone-id [this])
  (append-zone-region-id [this])
  (append-zone-or-offset-id [this])
  (append-zone-text [this text-style] [this text-style preferred-zones])
  (append-generic-zone-text [this text-style] [this text-style preferred-zones])
  (append-chronology-id [this])
  (append-chronology-text [this text-style])
  (append-localized [this date-style time-style])
  (append-literal [this append-literal--overloaded-param])
  (append [this formatter])
  (append-optional [this formatter])
  (optional-start [this])
  (optional-end [this])
  (append-pattern [this pattern])
  (pad-next [this pad-width] [this pad-width pad-char])
  (to-formatter [this] [this locale] [this resolver-style chrono]))

(s/def ::date-time-formatter-builder #(satisfies? IDateTimeFormatterBuilder %))