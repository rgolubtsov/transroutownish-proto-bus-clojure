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
    "The microservice entry point.

    Args:
        args: Arguments...
    " [& args]

    ;; The path and filename of the sample routes data store.
    (defmacro SAMPLE_ROUTES [] "./data/routes.txt")

    ;; The regex pattern for the element to be excluded
    ;; from a bus stops sequence: it is an arbitrary identifier
    ;; of a route, which is not used in the routes processing anyhow.
    (defmacro ROUTE_ID_REGEX [] "^\\d+")

    (println (str "This is a work in progress - "
                  "please wait for a while..."))
)

; vim:set nu et ts=4 sw=4:
