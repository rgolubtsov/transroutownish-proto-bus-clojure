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
        [clojure.java.io :as io]
    )

    (:import [java.util Scanner])

    (:require [com.transroutownish.proto.bus.helper :as AUX])
)

;; The path and filename of the sample routes data store.
(defmacro SAMPLE-ROUTES [] "./data/routes.txt")

;; The regex pattern for the element to be excluded
;; from a bus stops sequence: it is an arbitrary identifier
;; of a route, which is not used in the routes processing anyhow.
(defmacro ROUTE-ID-REGEX [] "^\\d+")

(defn -main
    "The microservice entry point.

    Args:
        args: A list of command-line arguments.
    " [& args]

    ; -------------------------------------------------------------------------
    ; --- Debug output - Begin ------------------------------------------------
    ; -------------------------------------------------------------------------
    (println (str "This is a work in progress - "
                  "please wait for a while..."))
    ; -------------------------------------------------------------------------
    ; --- Debug output - End --------------------------------------------------
    ; -------------------------------------------------------------------------

    ; Getting the daemon settings.
    (let [settings (AUX/-get-settings)]

    ; Getting the port number used to run the server,
    ; from daemon settings.
    (let [server-port (AUX/get-server-port settings)]

    (println server-port)
    )

    ; Getting the path and filename of the routes data store
    ; from daemon settings.
    (let [datastore0 (AUX/get-routes-datastore settings)]
    (let [datastore  (if (nil? datastore0) (SAMPLE-ROUTES) datastore0)]

    (try
        (let [data (io/file datastore)]

        (let [routes (Scanner. data)]

        (while (.hasNextLine routes)
            (println (str (.replaceFirst (.nextLine routes)
                (ROUTE-ID-REGEX) (AUX/EMPTY-STRING)) (AUX/SPACE)))
        )

        (.close routes)
        ))
    (catch java.io.FileNotFoundException e
        (binding [*out* *err*] (println (AUX/ERR-DATASTORE-NOT-FOUND)))

        (System/exit (AUX/EXIT-FAILURE))
    ))))

    ; Identifying whether debug logging is enabled.
    (let [debug-log-enabled (AUX/is-debug-log-enabled settings)]

    (println debug-log-enabled)
    ))
)

; vim:set nu et ts=4 sw=4:
