# Trans-RoutE-Townish (transroutownish) :small_orange_diamond: Urban bus routing microservice prototype (Clojure port)

**A daemon written in Clojure, designed and intended to be run as a microservice,
<br />implementing a simple urban bus routing prototype**

(*This is a work in progress &mdash; please wait for a while...*)

Consider an IoT system that aimed at planning and forming a specific bus route for a hypothetical passenger. One crucial part of such system is a **module**, that is responsible for filtering bus routes between two arbitrary bus stops where a direct route is actually present and can be easily found. Imagine there is a fictional urban public transportation agency that provides a wide series of bus routes which are covering large city areas, such that they are consisting of many bus stop points in each route. Let's name this agency **Trans-RoutE-Townish Co., Ltd.** or in the Net representation &mdash; **transroutownish.com**, hence the name of the project.

---

## Table of Contents

* **[Building](#building)**
* **[Running](#running)**
* **[Operating](#operating)**

## Building

The microservice is known to be built and run successfully under **Ubuntu Server (Ubuntu 20.04.3 LTS x86-64)**. Install the necessary dependencies (`openjdk-11-jre-headless`, `clojure`, `leiningen`, `make`, `docker.io`):

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
$ make      # <== Compilation phase. (Be aware: classes produced might be overwritten by the jar target.)
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
$ java -jar target/uberjar/bus-0.0.5-standalone.jar; echo $?
...
```

## Operating

(**TBD**)
