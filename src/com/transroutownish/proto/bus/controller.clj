;
; src/com/transroutownish/proto/bus/controller.clj
; =============================================================================
; Urban bus routing microservice prototype (Clojure port). Version 0.0.5
; =============================================================================
; A daemon written in Clojure, designed and intended to be run
; as a microservice, implementing a simple urban bus routing prototype.
; =============================================================================
; Copyright (C) 2021-2022 Radislav (Radicchio) Golubtsov
;
; (See the LICENSE file at the top of the source tree.)
;

(ns com.transroutownish.proto.bus.controller
    "The controller module of the daemon."

    (:require
        [clojure.tools.logging :as log]
        [clojure.walk          :refer [
            keywordize-keys
        ]]
        [clojure.string        :refer [
            split
        ]]
        [org.httpkit.server    :refer [
            run-server
            as-channel
            send!
        ]]
    )

    (:require [com.transroutownish.proto.bus.helper :as AUX])
)

; Helper constants.
(defmacro REST-PREFIX [] "route" )
(defmacro REST-DIRECT [] "direct")

(defmacro HTTP-200-OK        [] 200               )
(defmacro HDR-CONTENT-TYPE-N [] "content-type"    )
(defmacro HDR-CONTENT-TYPE-V [] "application/json")

; Extra helper constants.
(defmacro PARAMS-SEP [] #"=|&")

(def debug-log-enabled-ref
    "The Ref to the debug logging enabler."

    (ref [])
)

(def routes-vector-ref
    "The Ref to a vector containing all available routes."

    (ref [])
)

(defn find-direct-route
    "Performs the routes processing (onto bus stops sequences) to identify
    and return whether a particular interval between two bus stop points
    given is direct (i.e. contains in any of the routes), or not.

    Args:
        routes: A vector containing all available routes.

    Returns:
        true if the direct route is found, false otherwise.
    " {:added "0.0.1", :static true} [routes]

    (let [debug-log-enabled (nth @debug-log-enabled-ref 0)]

    (if debug-log-enabled
        (log/debug "Routes:" routes)
    ))
)

(defn reqhandler
    "The request handler callback.
    Gets called when a new incoming HTTP request is received.

    Args:
        req: The incoming HTTP request object.

    Returns:
        The HTTP status code, response headers, and a body of the response.
    " {:added "0.0.1", :static true} [req]

    (let [debug-log-enabled (nth @debug-log-enabled-ref 0)]

    (if (not debug-log-enabled)
        (log/debug "Request:" req)
    ))

    (let [method (get req :request-method)]
    (let [uri    (get req :uri           )]

    ; GET /route/direct
    (if (= method :get)
        (if (= uri (str (AUX/SLASH) (REST-PREFIX) (AUX/SLASH) (REST-DIRECT)))
            ; -----------------------------------------------------------------
            ; --- Parsing and validating request params - Begin ---------------
            ; -----------------------------------------------------------------
            (let [params0 (get req :query-string)]
            (let [params  (if (nil? params0) {:from 1 :to 1}
                (keywordize-keys (try
                    (apply hash-map (split params0 (PARAMS-SEP)))
                (catch IllegalArgumentException e
                    {:from 1 :to 1}
                )))
            )]
            ; -----------------------------------------------------------------
            ; --- Parsing and validating request params - End -----------------
            ; -----------------------------------------------------------------

            (let [from (get params :from)]
            (let [to   (get params :to  )]

            (as-channel req {:on-open (fn [channel] (send! channel {
                :status   (HTTP-200-OK)
                :headers {(HDR-CONTENT-TYPE-N) (HDR-CONTENT-TYPE-V)}
                :body     (str "{\"from\":" from ",\"to\":" to "}")
            }))})))))
        )
    )))

    ; Performing the routes processing to find out the direct route.
    (find-direct-route (nth @routes-vector-ref 0))
)

(defn startup
    "Starts up the daemon.

    Args:
        args: A list containing the server port number to listen on,
              as the first element.

    Returns:
        The exit code indicating the daemon overall termination status.
    " {:added "0.0.1", :static true} [args]

    (let [server-port       (nth args 0)]
    (let [debug-log-enabled (nth args 1)]
    (let [routes-vector     (nth args 2)]

    ; Starting an STM transaction to alter Refs:
    ; routes vector and debug log enabler.
    (dosync
        (alter debug-log-enabled-ref conj debug-log-enabled)
        (alter routes-vector-ref     conj routes-vector    )
    )

    (run-server reqhandler {:port server-port})
    )))
)

; vim:set nu et ts=4 sw=4:
