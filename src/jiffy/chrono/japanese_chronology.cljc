(ns jiffy.chrono.japanese-chronology
  (:require [clojure.spec.alpha :as s]
            [jiffy.dev.wip :refer [wip]]
            [jiffy.protocols.chrono.abstract-chronology :as abstract-chronology]
            [jiffy.protocols.chrono.chrono-local-date :as chrono-local-date]
            [jiffy.protocols.chrono.chrono-local-date-time :as chrono-local-date-time]
            [jiffy.protocols.chrono.chrono-zoned-date-time :as chrono-zoned-date-time]
            [jiffy.protocols.chrono.chronology :as chronology]
            [jiffy.protocols.chrono.era :as era]
            [jiffy.protocols.chrono.japanese-chronology :as japanese-chronology]
            [jiffy.protocols.chrono.japanese-era :as japanese-era]
            [jiffy.protocols.clock :as clock]
            [jiffy.chrono.japanese-date :as japanese-date]
            [jiffy.protocols.format.resolver-style :as resolver-style]
            [jiffy.protocols.instant :as instant]
            [jiffy.temporal.chrono-field :as chrono-field]
            [jiffy.protocols.temporal.temporal-accessor :as temporal-accessor]
            [jiffy.protocols.temporal.value-range :as value-range]
            [jiffy.protocols.time-comparable :as time-comparable]
            [jiffy.protocols.zone-id :as zone-id]
            [jiffy.specs :as j]))

(defrecord JapaneseChronology [])

(s/def ::create-args ::j/wip)
(defn create [])
(s/def ::japanese-chronology (j/constructor-spec JapaneseChronology create ::create-args))
(s/fdef create :args ::create-args :ret ::japanese-chronology)

(defmacro args [& x] `(s/tuple ::japanese-chronology ~@x))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L384
(s/def ::get-current-era-args (args))
(defn -get-current-era [this] (wip ::-get-current-era))
(s/fdef -get-current-era :args ::get-current-era-args :ret ::japanese-era/japanese-era)

(extend-type JapaneseChronology
  japanese-chronology/IJapaneseChronology
  (get-current-era [this] (-get-current-era this)))

;; FIXME: no implementation found from inherited class interface java.lang.Comparable

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L428
(s/def ::resolve-year-of-era-args (args ::j/wip ::resolver-style/resolver-style))
(defn -resolve-year-of-era [this field-values resolver-style] (wip ::-resolve-year-of-era))
(s/fdef -resolve-year-of-era :args ::resolve-year-of-era-args :ret ::chrono-local-date/chrono-local-date)

(extend-type JapaneseChronology
  abstract-chronology/IAbstractChronology
  (resolve-year-of-era [this field-values resolver-style] (-resolve-year-of-era this field-values resolver-style)))

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L158
(s/def ::get-id-args (args))
(defn -get-id [this] (wip ::-get-id))
(s/fdef -get-id :args ::get-id-args :ret string?)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L175
(s/def ::get-calendar-type-args (args))
(defn -get-calendar-type [this] (wip ::-get-calendar-type))
(s/fdef -get-calendar-type :args ::get-calendar-type-args :ret string?)

(s/def ::date-args (args ::j/wip))
(defn -date
  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L301
  ([this temporal] (wip ::-date))

  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L224
  ([this proleptic-year month day-of-month] (wip ::-date))

  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L203
  ([this era year-of-era month day-of-month] (wip ::-date)))
(s/fdef -date :args ::date-args :ret ::chrono-local-date/chrono-local-date)

(s/def ::date-year-day-args (args ::j/wip))
(defn -date-year-day
  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L269
  ([this proleptic-year day-of-year] (wip ::-date-year-day))

  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L251
  ([this era year-of-era day-of-year] (wip ::-date-year-day)))
(s/fdef -date-year-day :args ::date-year-day-args :ret ::japanese-date/japanese-date)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L281
(s/def ::date-epoch-day-args (args ::j/long))
(defn -date-epoch-day [this epoch-day] (wip ::-date-epoch-day))
(s/fdef -date-epoch-day :args ::date-epoch-day-args :ret ::japanese-date/japanese-date)

(s/def ::date-now-args (args ::j/wip))
(defn -date-now
  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L286
  ([this] (wip ::-date-now))

  ;; NB! This method is overloaded!
;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L291
  ([this date-now--overloaded-param] (wip ::-date-now)))
(s/fdef -date-now :args ::date-now-args :ret ::chrono-local-date/chrono-local-date)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L310
(s/def ::local-date-time-args (args ::temporal-accessor/temporal-accessor))
(defn -local-date-time [this temporal] (wip ::-local-date-time))
(s/fdef -local-date-time :args ::local-date-time-args :ret ::chrono-local-date-time/chrono-local-date-time)

(s/def ::zoned-date-time-args (args ::j/wip))
(defn -zoned-date-time
  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L316
  ([this temporal] (wip ::-zoned-date-time))

  ;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L322
  ([this instant zone] (wip ::-zoned-date-time)))
(s/fdef -zoned-date-time :args ::zoned-date-time-args :ret ::chrono-zoned-date-time/chrono-zoned-date-time)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L338
(s/def ::is-leap-year-args (args ::j/long))
(defn -is-leap-year [this proleptic-year] (wip ::-is-leap-year))
(s/fdef -is-leap-year :args ::is-leap-year-args :ret ::j/boolean)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L343
(s/def ::proleptic-year-args (args ::era/era ::j/int))
(defn -proleptic-year [this era year-of-era] (wip ::-proleptic-year))
(s/fdef -proleptic-year :args ::proleptic-year-args :ret ::j/int)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L353
(s/def ::eras-args (args))
(defn -eras [this] (wip ::-eras))
(s/fdef -eras :args ::eras-args :ret ::j/wip)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L375
(s/def ::era-of-args (args ::j/int))
(defn -era-of [this era-value] (wip ::-era-of))
(s/fdef -era-of :args ::era-of-args :ret ::japanese-era/japanese-era)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L392
(s/def ::range-args (args ::chrono-field/chrono-field))
(defn -range [this field] (wip ::-range))
(s/fdef -range :args ::range-args :ret ::value-range/value-range)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L423
(s/def ::resolve-date-args (args ::j/wip ::resolver-style/resolver-style))
(defn -resolve-date [this field-values resolver-style] (wip ::-resolve-date))
(s/fdef -resolve-date :args ::resolve-date-args :ret ::japanese-date/japanese-date)

(extend-type JapaneseChronology
  chronology/IChronology
  (get-id [this] (-get-id this))
  (get-calendar-type [this] (-get-calendar-type this))
  (date
    ([this temporal] (-date this temporal))
    ([this proleptic-year month day-of-month] (-date this proleptic-year month day-of-month))
    ([this era year-of-era month day-of-month] (-date this era year-of-era month day-of-month)))
  (date-year-day
    ([this proleptic-year day-of-year] (-date-year-day this proleptic-year day-of-year))
    ([this era year-of-era day-of-year] (-date-year-day this era year-of-era day-of-year)))
  (date-epoch-day [this epoch-day] (-date-epoch-day this epoch-day))
  (date-now
    ([this] (-date-now this))
    ([this date-now--overloaded-param] (-date-now this date-now--overloaded-param)))
  (local-date-time [this temporal] (-local-date-time this temporal))
  (zoned-date-time
    ([this temporal] (-zoned-date-time this temporal))
    ([this instant zone] (-zoned-date-time this instant zone)))
  (is-leap-year [this proleptic-year] (-is-leap-year this proleptic-year))
  (proleptic-year [this era year-of-era] (-proleptic-year this era year-of-era))
  (eras [this] (-eras this))
  (era-of [this era-value] (-era-of this era-value))
  (range [this field] (-range this field))
  (resolve-date [this field-values resolver-style] (-resolve-date this field-values resolver-style)))


;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L124
(def JCAL ::JCAL--not-implemented)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L128
(def LOCALE ::LOCALE--not-implemented)

;; https://github.com/unofficial-openjdk/openjdk/tree/cec6bec2602578530214b2ce2845a863da563c3d/src/java.base/share/classes/java/time/chrono/JapaneseChronology.java#L133
(def INSTANCE ::INSTANCE--not-implemented)
