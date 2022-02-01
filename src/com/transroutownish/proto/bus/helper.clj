;
; src/com/transroutownish/proto/bus/helper.clj
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

(ns com.transroutownish.proto.bus.helper
    "The helper module for the daemon."

    (:require
        [clojure.java.io :as io ]
        [clojure.edn     :as edn]
    )
)

; Helper constants.
(defmacro EMPTY-STRING []   "")
(defmacro SLASH        []  "/")
(defmacro EQUALS       []  "=")
(defmacro BRACES       [] "{}")
(defmacro SPACE        []  " ")
(defmacro V-BAR        []  "|")
(defmacro NEW-LINE     [] "\n")

; Extra helper constants.
(defmacro YES [] "yes")

; Common error messages.
(defmacro ERR-DATASTORE-NOT-FOUND              []
          "FATAL: Data store file not found. Quitting...")
(defmacro ERR-REQ-PARAMS-MUST-BE-POSITIVE-INTS []
          (str "Request parameters must take positive integer values, "
               "in the range 1 .. 2,147,483,647. Please check your inputs."))

;; The daemon settings filename.
(defmacro SETTINGS [] "settings.edn")

(defn get-routes-datastore
    "Retrieves the path and filename of the routes data store
    from daemon settings.

    Returns:
        The path and filename of the routes data store
        or nil, if they are not defined.
    " []

    (let [settings (edn/read-string (slurp (io/resource (SETTINGS))))]

    (let [server-port                  (some :server-port                  settings)]
    (let [routes-datastore-path-prefix (some :routes-datastore-path-prefix settings)]
    (let [routes-datastore-path-dir    (some :routes-datastore-path-dir    settings)]
    (let [routes-datastore-filename    (some :routes-datastore-filename    settings)]

    ; -------------------------------------------------------------------------
    ; --- Debug output - Begin ------------------------------------------------
    ; -------------------------------------------------------------------------
    (println (str server-port                  (NEW-LINE)
                  routes-datastore-path-prefix (NEW-LINE)
                  routes-datastore-path-dir    (NEW-LINE)
                  routes-datastore-filename))
    ; -------------------------------------------------------------------------
    ; --- Debug output - End --------------------------------------------------
    ; -------------------------------------------------------------------------

    (let [datastore0 (if (nil? routes-datastore-path-prefix) (EMPTY-STRING)
                               routes-datastore-path-prefix)]
    (let [datastore1 (if (nil? routes-datastore-path-dir   ) (EMPTY-STRING)
                               routes-datastore-path-dir   )]
    (let [datastore2 (if (nil? routes-datastore-filename   ) (EMPTY-STRING)
                               routes-datastore-filename   )]
    (let [datastore  (str  datastore0 datastore1 datastore2)]
        (if (= datastore (EMPTY-STRING)) nil datastore) ; <== Return value.
    )))))))))
)

; vim:set nu et ts=4 sw=4:
