(ns jiffy.parity-tests.local-time-test
  (:require [jiffy.parity-tests.support :refer [test-proto-fn test-proto-fn!]]))

(test-proto-fn jiffy.local-time jiffy.local-time/to-nano-of-day)
(test-proto-fn jiffy.local-time jiffy.local-time/to-second-of-day)
(test-proto-fn jiffy.local-time jiffy.local-time/get-hour)
(test-proto-fn jiffy.local-time jiffy.local-time/get-minute)
(test-proto-fn jiffy.local-time jiffy.local-time/get-second)
(test-proto-fn jiffy.local-time jiffy.local-time/get-nano)
(test-proto-fn jiffy.local-time jiffy.local-time/with-hour)
(test-proto-fn jiffy.local-time jiffy.local-time/with-minute)
(test-proto-fn jiffy.local-time jiffy.local-time/with-second)
(test-proto-fn jiffy.local-time jiffy.local-time/with-nano)
;;(test-proto-fn jiffy.local-time jiffy.local-time/truncated-to)
(test-proto-fn jiffy.local-time jiffy.local-time/plus-hours)
(test-proto-fn jiffy.local-time jiffy.local-time/plus-minutes)
(test-proto-fn jiffy.local-time jiffy.local-time/plus-seconds)
(test-proto-fn jiffy.local-time jiffy.local-time/plus-nanos)
(test-proto-fn jiffy.local-time jiffy.local-time/minus-hours)
(test-proto-fn jiffy.local-time jiffy.local-time/minus-minutes)
(test-proto-fn jiffy.local-time jiffy.local-time/minus-seconds)
(test-proto-fn jiffy.local-time jiffy.local-time/minus-nanos)
;;(test-proto-fn jiffy.local-time jiffy.local-time/format)
;;(test-proto-fn jiffy.local-time jiffy.local-time/at-date)
;;(test-proto-fn jiffy.local-time jiffy.local-time/at-offset)
;;(test-proto-fn jiffy.local-time jiffy.local-time/to-epoch-second)
(test-proto-fn jiffy.local-time jiffy.local-time/is-after)
(test-proto-fn jiffy.local-time jiffy.local-time/is-before)
