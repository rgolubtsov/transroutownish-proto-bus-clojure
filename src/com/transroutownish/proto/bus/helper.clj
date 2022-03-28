;
; src/com/transroutownish/proto/bus/helper.clj
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

(ns com.transroutownish.proto.bus.helper
    "The helper module for the daemon."

    (:require
        [clojure.tools.logging :as log]
        [clojure.java.io       :as io ]
        [clojure.edn           :as edn]
    )
)

; Helper constants.
(defmacro EXIT-FAILURE []    1) ;    Failing exit status.
(defmacro EXIT-SUCCESS []    0) ; Successful exit status.
(defmacro EMPTY-STRING []   "")
(defmacro SLASH        []  "/")
(defmacro EQUALS       []  "=")
(defmacro SPACE        []  " ")
(defmacro V-BAR        []  "|")
(defmacro NEW-LINE     [] "\n")

; Extra helper constants.
(defmacro YES [] "yes")

; Common error messages.
(defmacro ERR-PORT-VALID-MUST-BE-POSITIVE-INT  []
          (str "Valid server port must be a positive integer value, "
               "in the range 1024 .. 49151. The default value of 8080 "
               "will be used instead."))
(defmacro ERR-DATASTORE-NOT-FOUND              []
          "FATAL: Data store file not found. Quitting...")
(defmacro ERR-REQ-PARAMS-MUST-BE-POSITIVE-INTS []
          (str "Request parameters must take positive integer values, "
               "in the range 1 .. 2,147,483,647. Please check your inputs."))

(defmacro MIN-PORT
    "The minimum port number allowed."

    []  1024
)

(defmacro MAX-PORT
    "The maximum port number allowed."

    [] 49151
)

(defmacro DEF-PORT
    "The default server port number."

    []  8080
)

(defmacro SETTINGS
    "The daemon settings filename."

    [] "settings.edn"
)

(defn get-server-port
    "Retrieves the port number used to run the server, from daemon settings.

    Args:
        The vector containing maps of individual settings.

    Returns:
        The port number on which the server has to be run.
    " {:added "0.0.1"} [settings]

    (let [server-port (some :server-port settings)]

    (if (not (nil? server-port))
        (cond
            (and (>= server-port (MIN-PORT)) (<= server-port (MAX-PORT)))
                (list server-port)
            :else
                (do (log/warn (ERR-PORT-VALID-MUST-BE-POSITIVE-INT))

                (list (DEF-PORT)))
        )
        (do (log/warn (ERR-PORT-VALID-MUST-BE-POSITIVE-INT))

        (list (DEF-PORT)))
    ))
)

(defn get-routes-datastore
    "Retrieves the path and filename of the routes data store
    from daemon settings.

    Args:
        The vector containing maps of individual settings.

    Returns:
        The path and filename of the routes data store
        or `nil`, if they are not defined.
    " {:added "0.0.1"} [settings]

    (let [routes-datastore-path-prefix (some :routes-datastore-path-prefix settings)]
    (let [routes-datastore-path-dir    (some :routes-datastore-path-dir    settings)]
    (let [routes-datastore-filename    (some :routes-datastore-filename    settings)]

    (let [datastore0 (if (nil? routes-datastore-path-prefix) (EMPTY-STRING)
                               routes-datastore-path-prefix)]
    (let [datastore1 (if (nil? routes-datastore-path-dir   ) (EMPTY-STRING)
                               routes-datastore-path-dir   )]
    (let [datastore2 (if (nil? routes-datastore-filename   ) (EMPTY-STRING)
                               routes-datastore-filename   )]
    (let [datastore  (str  datastore0 datastore1 datastore2)]
        (if (= datastore (EMPTY-STRING)) nil datastore) ; <== Return value.
    )))))))
)

(defn is-debug-log-enabled
    "Identifies whether debug logging is enabled by retrieving
    the corresponding value from daemon settings.

    Args:
        The vector containing maps of individual settings.

    Returns:
        `true` if debug logging is enabled, `false` otherwise.
    " {:added "0.0.1"} [settings]

    (let [logger-debug-enabled (some :logger-debug-enabled settings)]

    (and (not (nil?    logger-debug-enabled))
         (=   (compare logger-debug-enabled (YES)) 0)))
)

(defn -get-settings
    "Helper function. Used to get the daemon settings.

    Returns:
        A vector containing maps of individual settings.
    " {:added "0.0.1"} []

    (edn/read-string (slurp (io/resource (SETTINGS))))
)

; vim:set nu et ts=4 sw=4:
