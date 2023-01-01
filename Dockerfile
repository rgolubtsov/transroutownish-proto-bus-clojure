#
# Dockerfile
# =============================================================================
# Urban bus routing microservice prototype (Clojure port). Version 0.15.2
# =============================================================================
# A daemon written in Clojure, designed and intended to be run
# as a microservice, implementing a simple urban bus routing prototype.
# =============================================================================
# Copyright (C) 2021-2023 Radislav (Radicchio) Golubtsov
#
# (See the LICENSE file at the top of the source tree.)
#

# Note: Since it is supposed that all-in-one JAR file was already built
#       previously and will be run in the container as is, there is no need
#       to use official Clojure images (https://hub.docker.com/_/clojure).
#       Instead, it is recommended to use any JRE-only flavors of slim
#       (e.g. Alpine) images.
FROM       azul/zulu-openjdk-alpine:11-jre-headless-latest
USER       daemon
WORKDIR    var/tmp
COPY       target/uberjar/*.jar bus.jar
COPY       data data/
ENTRYPOINT ["java", "-jar", "bus.jar"]

# vim:set nu ts=4 sw=4:
