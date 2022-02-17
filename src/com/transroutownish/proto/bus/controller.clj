;
; src/com/transroutownish/proto/bus/controller.clj
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

(ns com.transroutownish.proto.bus.controller
    "The controller module of the daemon."

    (:require
        [clojure.tools.logging :as log]
        [org.httpkit.server    :refer [
            run-server
        ]]
    )
)

(def ^:dynamic routes-vector [])

(defn find-direct-route
    "Performs the routes processing (onto bus stops sequences) to identify
    and return whether a particular interval between two bus stop points
    given is direct (i.e. contains in any of the routes), or not.

    Args:
        routes: A vector containing all available routes.

    Returns:
        true if the direct route is found, false otherwise.
    " {:added "0.0.1", :static true} [routes]

    (log/debug "Routes:" routes)
)

(defn reqhandler
    "The request handler callback.
    Gets called when a new incoming HTTP request is received.

    Args:
        req: The incoming HTTP request object.

    Returns:
        The HTTP status code, response headers, and a body of the response.
    " {:added "0.0.1", :static true} [req]

    (log/debug "Request:" req)

    (find-direct-route routes-vector)
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
    (let [routes            (nth args 2)]

    (log/debug "HTTP Kit server port number:" server-port)
    (log/debug "Debug log enabled:" debug-log-enabled)

    (binding [routes-vector routes]

    (run-server reqhandler {:port server-port})
    ))))
)

; vim:set nu et ts=4 sw=4:
