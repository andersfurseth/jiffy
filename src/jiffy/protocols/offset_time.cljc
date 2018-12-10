(ns jiffy.protocols.offset-time
  (:refer-clojure :exclude [format ])
  (:require [clojure.spec.alpha :as s]))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/OffsetTime.java
(defprotocol IOffsetTime
  (get-offset [this])
  (with-offset-same-local [this offset])
  (with-offset-same-instant [this offset])
  (to-local-time [this])
  (get-hour [this])
  (get-minute [this])
  (get-second [this])
  (get-nano [this])
  (with-hour [this hour])
  (with-minute [this minute])
  (with-second [this second])
  (with-nano [this nano-of-second])
  (truncated-to [this unit])
  (plus-hours [this hours])
  (plus-minutes [this minutes])
  (plus-seconds [this seconds])
  (plus-nanos [this nanos])
  (minus-hours [this hours])
  (minus-minutes [this minutes])
  (minus-seconds [this seconds])
  (minus-nanos [this nanos])
  (format [this formatter])
  (at-date [this date])
  (to-epoch-second [this date])
  (is-after [this other])
  (is-before [this other])
  (is-equal [this other]))

(s/def ::offset-time #(satisfies? IOffsetTime %))