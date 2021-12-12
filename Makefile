#
# Makefile
# =============================================================================
# Urban bus routing microservice prototype (Clojure port). Version 0.0.1
# =============================================================================
# A daemon written in Clojure, designed and intended to be run
# as a microservice, implementing a simple urban bus routing prototype.
# =============================================================================
# Copyright (C) 2021 Radislav (Radicchio) Golubtsov
#
# (See the LICENSE file at the top of the source tree.)
#

SERV = target

# Specify flags and other vars here.
ECHO = @echo

# Making the first target (the microservice itself).
$(SERV):
	$(ECHO)

.PHONY: all clean

all: $(SERV)

clean:
	$(ECHO)

# vim:set nu ts=4 sw=4:
