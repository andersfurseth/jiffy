(ns jiffy.parity-tests.zone-offset-test
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [jiffy.conversion :as conversion]
            [jiffy.parity-tests.support :refer [test-proto-fn test-static-fn]]
            [jiffy.specs :as j]
            [jiffy.temporal.chrono-field :as chrono-field]
            [jiffy.temporal.temporal-field :as temporal-field]))

(s/def ::temporal-field/temporal-field
  (s/with-gen ::temporal-field/temporal-field
    (fn [] (gen/one-of (for [enum (chrono-field/values)]
                         (gen/return enum))))))

(test-proto-fn jiffy.zone-offset jiffy.zone-offset/get-total-seconds)
(test-proto-fn jiffy.zone-offset jiffy.time-comparable/compare-to)
(test-proto-fn jiffy.zone-offset jiffy.zone-id/get-id)
;; (test-proto-fn jiffy.zone-offset jiffy.zone-id/get-rules)
(test-proto-fn jiffy.zone-offset jiffy.temporal.temporal-accessor/is-supported)
(test-proto-fn jiffy.zone-offset jiffy.temporal.temporal-accessor/range)
(test-proto-fn jiffy.zone-offset jiffy.temporal.temporal-accessor/get)
(test-proto-fn jiffy.zone-offset jiffy.temporal.temporal-accessor/get-long)
;; (test-proto-fn jiffy.zone-offset jiffy.temporal.temporal-accessor/query)
;; (test-proto-fn jiffy.zone-offset jiffy.temporal.temporal-adjuster/adjust-into)

;; TODO: these fail due to spec generator producing Long, not Integer as required by
;; call to java. Coerce from Long to Integer in `jiffy.parity-tests.support/invoke-java`

;; (test-static-fn jiffy.zone-offset/of-hours)
;; (test-static-fn jiffy.zone-offset/of-hours-minutes)
;; (test-static-fn jiffy.zone-offset/of-hours-minutes-seconds)
;; (test-static-fn jiffy.zone-offset/of-total-seconds)
;; (test-static-fn jiffy.zone-offset/of)
;; (test-static-fn jiffy.zone-offset/from)
