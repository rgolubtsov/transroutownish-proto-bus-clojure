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
$ lein compile
$
$ lein test
...
$ lein jar
...
```

Or **build** the microservice using **GNU Make** (optional, but for convenience &mdash; it covers the same **Leiningen** build workflow under the hood):

(**TBD**)

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

(**TBD**)

```
$ ./busd; echo $?
...
```

## Operating

(**TBD**)
