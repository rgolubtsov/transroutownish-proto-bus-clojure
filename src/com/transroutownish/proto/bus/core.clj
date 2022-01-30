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

    (:require
        [clojure.java.io :as io ]
        [clojure.edn     :as edn]
    )

    (:require [com.transroutownish.proto.bus.helper :as AUX])
)

(defn -main
    "The microservice entry point.

    Args:
        args: A list of command-line arguments.
    " [& args]

    ;; The path and filename of the sample routes data store.
    (defmacro SAMPLE-ROUTES [] "./data/routes.txt")

    ;; The regex pattern for the element to be excluded
    ;; from a bus stops sequence: it is an arbitrary identifier
    ;; of a route, which is not used in the routes processing anyhow.
    (defmacro ROUTE-ID-REGEX [] #"^\d+")

    (let [settings (edn/read-string (slurp (io/resource (AUX/APP-PROPS))))]

    (let [server-port                  (get (nth settings 0) :server-port                 )]
    (let [routes-datastore-path-prefix (get (nth settings 1) :routes-datastore-path-prefix)]
    (let [routes-datastore-path-dir    (get (nth settings 2) :routes-datastore-path-dir   )]
    (let [routes-datastore-filename    (get (nth settings 3) :routes-datastore-filename   )]

    ; -------------------------------------------------------------------------
    ; --- Debug output - Begin ------------------------------------------------
    ; -------------------------------------------------------------------------
    (println (str "This is a work in progress - "
                  "please wait for a while..."))

    (println (str server-port                  (AUX/NEW-LINE)
                  routes-datastore-path-prefix (AUX/NEW-LINE)
                  routes-datastore-path-dir    (AUX/NEW-LINE)
                  routes-datastore-filename))
    ; -------------------------------------------------------------------------
    ; --- Debug output - End --------------------------------------------------
    ; -------------------------------------------------------------------------

    )))))
)

; vim:set nu et ts=4 sw=4:
