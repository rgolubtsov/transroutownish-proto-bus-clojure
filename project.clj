;
; project.clj
; =============================================================================
; Urban bus routing microservice prototype (Clojure port). Version 0.10.1
; =============================================================================
; A daemon written in Clojure, designed and intended to be run
; as a microservice, implementing a simple urban bus routing prototype.
; =============================================================================
; Copyright (C) 2021-2022 Radislav (Radicchio) Golubtsov
;
; (See the LICENSE file at the top of the source tree.)
;

(defproject bus   "0.10.1"
    :description  "Urban bus routing microservice prototype."
    :url          "https://github.com/rgolubtsov/transroutownish-proto-bus-clojure"
    :license {
        :name "MIT License"
        :url  "https://raw.githubusercontent.com/rgolubtsov/transroutownish-proto-bus-clojure/main/LICENSE"
    }
    :dependencies [
        [org.clojure/clojure       "1.11.0"]
        [org.clojure/tools.logging "1.2.4" ]
        [org.clojure/data.json     "2.4.0" ]
        [org.slf4j/slf4j-reload4j  "1.7.36"]
        [org.graylog2/syslog4j     "0.9.60"]
        [net.java.dev.jna/jna      "5.11.0"]
        [http-kit                  "2.5.3" ]
    ]
    :main         com.transroutownish.proto.bus.core
    :target-path  "target/%s"
    :profiles     {:uberjar {:aot :all}}
    :jvm-opts     ["-Dclojure.tools.logging.factory=clojure.tools.logging.impl/slf4j-factory"]
)

; vim:set nu et ts=4 sw=4:
