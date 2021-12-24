# Trans-RoutE-Townish (transroutownish) :small_orange_diamond: Urban bus routing microservice prototype (Clojure port)

**A daemon written in Clojure, designed and intended to be run as a microservice,
<br />implementing a simple urban bus routing prototype**

(*This is a work in progress &mdash; please wait for a while...*)

---

## Table of Contents

* **[Building](#building)**
* **[Running](#running)**
* **[Operating](#operating)**

## Building

Install the necessary dependencies (`openjdk-11-jre-headless`, `clojure`, `leiningen`, `make`, `docker.io`):

```
$ sudo apt-get update && \
  sudo apt-get install openjdk-11-jre-headless clojure leiningen make docker.io -y
```

**Build** the microservice using **Leiningen**:

```
$ lein clean
$
$ lein compile :all
...
$ lein test  # <== Optional. This currently does nothing.
...
$ lein uberjar
...
```

Or **build** the microservice using **GNU Make** (optional, but for convenience &mdash; it covers the same **Leiningen** build workflow under the hood):

```
$ make clean
...
$ make      # <== Compilation phase. (But produced classes might be overwritten by the jar target.)
...
$ make test
...
$ make jar
...
$ make all  # <== This is equivalent to the jar target.
...
```

## Running

**Run** the microservice using **Leiningen** (generally for development and debugging purposes):

```
$ lein run; echo $?
$ #       ^   ^   ^
$ #       |   |   |
$ # ------+---+---+
$ # Whilst this is not necessary, it's beneficial knowing the exit code.
...
```

**Run** the microservice using its all-in-one JAR file, built previously by the `uberjar` target:

```
$ java -jar target/uberjar/bus-0.0.1-standalone.jar; echo $?
...
```

## Operating

(**TBD**)
