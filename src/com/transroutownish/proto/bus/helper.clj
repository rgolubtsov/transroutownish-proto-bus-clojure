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
(defmacro EMPTY_STRING []   "")
(defmacro SLASH        []  "/")
(defmacro EQUALS       []  "=")
(defmacro BRACES       [] "{}")
(defmacro SPACE        []  " ")
(defmacro V_BAR        []  "|")

; Extra helper constants.
(defmacro YES [] "yes")

; Common error messages.
(defmacro ERR_APP_PROPS_UNABLE_TO_GET          []
          "Unable to get application properties.")
(defmacro ERR_DATASTORE_NOT_FOUND              []
          "FATAL: Data store file not found. Quitting...")
(defmacro ERR_REQ_PARAMS_MUST_BE_POSITIVE_INTS []
          (str "Request parameters must take positive integer values, "
               "in the range 1 .. 2,147,483,647. Please check your inputs."))

; vim:set nu et ts=4 sw=4:
