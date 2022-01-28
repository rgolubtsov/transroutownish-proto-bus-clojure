;
; src/com/transroutownish/proto/bus/core.clj
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

(ns com.transroutownish.proto.bus.core
    "The main module of the daemon."

    (:gen-class)
)

(defn -main
    "Starts up the daemon.

    Args:
        args: Arguments...
    " [& args]

    (println (str "This is a work in progress - "
                  "please wait for a while..."))
)

; vim:set nu et ts=4 sw=4:
