#
# Makefile
# =============================================================================
# Urban bus routing microservice prototype (Clojure port). Version 0.0.9
# =============================================================================
# A daemon written in Clojure, designed and intended to be run
# as a microservice, implementing a simple urban bus routing prototype.
# =============================================================================
# Copyright (C) 2021-2022 Radislav (Radicchio) Golubtsov
#
# (See the LICENSE file at the top of the source tree.)
#

SERV    = target
TEST    = test
JAR     = jar
UBERJAR = uberjar
VERSION = 0.0.9

# Specify flags and other vars here.
LEIN    = lein
MV      = mv
MVFLAGS = -v
ECHO    = @echo

# Making the first target (the microservice itself).
$(SERV):
	$(LEIN) compile :all

# Making the second target (tests).
$(TEST):
	$(LEIN) $(TEST)

# Making the third target (runnable JAR file).
$(JAR):
	$(LEIN) $(UBERJAR)
	$(MV) $(MVFLAGS) $(SERV)/$(UBERJAR)/bus-$(VERSION).$(JAR)            \
	      $(SERV)/$(UBERJAR)/bus-$(VERSION).$(JAR).original
	$(MV) $(MVFLAGS) $(SERV)/$(UBERJAR)/bus-$(VERSION)-standalone.$(JAR) \
	      $(SERV)/$(UBERJAR)/bus-$(VERSION).$(JAR)
	$(ECHO)

.PHONY: all clean

all: $(JAR)

clean:
	$(LEIN) clean

# vim:set nu ts=4 sw=4:
