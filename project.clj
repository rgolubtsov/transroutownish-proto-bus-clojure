;
; project.clj
; =============================================================================
; Urban bus routing microservice prototype (Clojure port). Version 0.20.0
; =============================================================================
; A daemon written in Clojure, designed and intended to be run
; as a microservice, implementing a simple urban bus routing prototype.
; =============================================================================
; Copyright (C) 2021-2023 Radislav (Radicchio) Golubtsov
;
; (See the LICENSE file at the top of the source tree.)
;

(defproject bus   "0.20.0"
    :description  "Urban bus routing microservice prototype."
    :url          "https://github.com/rgolubtsov/transroutownish-proto-bus-clojure"
    :license {
        :name "MIT License"
        :url  "https://raw.githubusercontent.com/rgolubtsov/transroutownish-proto-bus-clojure/main/LICENSE"
    }
    :dependencies [
        [org.clojure/clojure       "1.11.1"]
        [org.clojure/tools.logging "1.2.4" ]
        [org.clojure/data.json     "2.4.0" ]
        [org.slf4j/slf4j-api       "2.0.7" ]
        [org.slf4j/slf4j-reload4j  "2.0.7" ]
        [org.graylog2/syslog4j     "0.9.61"]
        [net.java.dev.jna/jna      "5.13.0"]
        [http-kit                  "2.7.0" ]
    ]
    :main         com.transroutownish.proto.bus.core
    :target-path  "target/%s"
    :profiles     {:uberjar {:aot :all}}
    :jvm-opts     ["-Dclojure.tools.logging.factory=clojure.tools.logging.impl/slf4j-factory"]
)

; vim:set nu et ts=4 sw=4:
