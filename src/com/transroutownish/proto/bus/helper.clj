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
(defmacro ERR-APP-PROPS-UNABLE-TO-GET          []
          "Unable to get application properties.")
(defmacro ERR-DATASTORE-NOT-FOUND              []
          "FATAL: Data store file not found. Quitting...")
(defmacro ERR-REQ-PARAMS-MUST-BE-POSITIVE-INTS []
          (str "Request parameters must take positive integer values, "
               "in the range 1 .. 2,147,483,647. Please check your inputs."))

;; The application properties filename.
(defmacro APP-PROPS [] "application.properties")

; Application properties keys for the logger.
(defmacro DEBUG-LOG-ENABLED [] "logger.debug.enabled")

; Application properties keys for the routes data store.
(defmacro PATH-PREFIX [] "routes.datastore.path.prefix")
(defmacro PATH-DIR    [] "routes.datastore.path.dir"   )
(defmacro FILENAME    [] "routes.datastore.filename"   )

; vim:set nu et ts=4 sw=4:
