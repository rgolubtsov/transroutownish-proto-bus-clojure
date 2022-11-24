;
; src/com/transroutownish/proto/bus/core.clj
; =============================================================================
; Urban bus routing microservice prototype (Clojure port). Version 0.15.2
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
        [clojure.tools.logging :as log]
        [clojure.java.io       :as io ]
    )

    (:import [java.util Scanner])

    (:require
        [com.transroutownish.proto.bus.controller :as CTRL]
        [com.transroutownish.proto.bus.helper     :as AUX ]
    )
)

(defmacro SAMPLE-ROUTES
    "The path and filename of the sample routes data store."

    [] "./data/routes.txt"
)

(defmacro ROUTE-ID-REGEX
    "The regex pattern for the element to be excluded from a bus stops
    sequence: it is an arbitrary identifier of a route,
    which is not used in the routes processing anyhow."

    [] "^\\d+"
)

(defn -main
    "The microservice entry point.

    Args:
        args: A list of command-line arguments.
    " {:added "0.0.1"} [& args]

    ; Getting the daemon settings.
    (let [settings (AUX/-get-settings)]

    ; Getting the port number used to run the server,
    ; from daemon settings.
    (let [server-port (AUX/get-server-port settings)]

    ; Identifying whether debug logging is enabled.
    (let [debug-log-enabled (AUX/is-debug-log-enabled settings)]

    ; Getting the path and filename of the routes data store
    ; from daemon settings.
    (let [datastore0 (AUX/get-routes-datastore settings)]
    (let [datastore  (if (nil? datastore0) (SAMPLE-ROUTES) datastore0)]

    (try
        (let [data (io/file datastore)]

        (let [routes (Scanner. data)]

        (let [routes-vector ; <== Like routes_list = new ArrayList();

        (loop [-routes-vector []] (if (.hasNextLine routes)
            (recur (conj -routes-vector (str (.replaceFirst (.nextLine routes)
                (ROUTE-ID-REGEX) (AUX/EMPTY-STRING)) (AUX/SPACE)))
            )  -routes-vector) ; <== Return value from a loop.
        )]

        (.close routes)

        ; Starting up the daemon.
        (CTRL/startup (list
            (nth server-port 0)
            debug-log-enabled
            routes-vector
        )))))
    (catch java.io.FileNotFoundException e
        (log/fatal (AUX/ERR-DATASTORE-NOT-FOUND))

        (System/exit (AUX/EXIT-FAILURE))
    )))))))
)

; vim:set nu et ts=4 sw=4:
