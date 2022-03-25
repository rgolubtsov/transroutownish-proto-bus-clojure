;
; test/com/transroutownish/proto/bus/core_test.clj
; =============================================================================
; Urban bus routing microservice prototype (Clojure port). Version 0.0.9
; =============================================================================
; A daemon written in Clojure, designed and intended to be run
; as a microservice, implementing a simple urban bus routing prototype.
; =============================================================================
; Copyright (C) 2021-2022 Radislav (Radicchio) Golubtsov
;
; (See the LICENSE file at the top of the source tree.)
;

(ns com.transroutownish.proto.bus.core-test
    "The test module for the daemon."

    (:require
        [clojure.test                       :refer :all]
        [com.transroutownish.proto.bus.core :refer :all]
    )
)

(deftest a-test
    (testing "Dummy for a while... Does nothing."
        (is (= 1 1))
    )
)

; vim:set nu et ts=4 sw=4:
