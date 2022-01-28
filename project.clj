;
; project.clj
; =============================================================================
; Urban bus routing microservice prototype (Clojure port). Version 0.0.1
; =============================================================================
; A daemon written in Clojure, designed and intended to be run
; as a microservice, implementing a simple urban bus routing prototype.
; =============================================================================
; Copyright (C) 2021-2022 Radislav (Radicchio) Golubtsov
;
; (See the LICENSE file at the top of the source tree.)
;

(defproject bus   "0.0.1"
    :description  "Urban bus routing microservice prototype."
    :url          "https://github.com/rgolubtsov/transroutownish-proto-bus-clojure"
    :license {
        :name "MIT License"
        :url  "https://raw.githubusercontent.com/rgolubtsov/transroutownish-proto-bus-clojure/main/LICENSE"
    }
    :dependencies [[org.clojure/clojure "1.10.1"]]
    :main         com.transroutownish.proto.bus.core
    :target-path  "target/%s"
    :profiles     {:uberjar {:aot :all}}
)

; vim:set nu et ts=4 sw=4: