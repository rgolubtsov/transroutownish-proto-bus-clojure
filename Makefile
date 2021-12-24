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
TEST = test
JAR  = jar

# Specify flags and other vars here.
LEIN = lein
ECHO = @echo

# Making the first target (the microservice itself).
$(SERV):
	$(LEIN) compile :all

# Making the second target (tests).
$(TEST):
	$(LEIN) $(TEST)

# Making the third target (runnable JAR file).
$(JAR):
	$(LEIN) uberjar
	$(ECHO)

.PHONY: all clean

all: $(JAR)

clean:
	$(LEIN) clean

# vim:set nu ts=4 sw=4:
