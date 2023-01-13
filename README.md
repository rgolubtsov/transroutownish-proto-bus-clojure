# Trans-RoutE-Townish (transroutownish) :small_orange_diamond: Urban bus routing microservice prototype (Clojure port)

**A daemon written in Clojure, designed and intended to be run as a microservice,
<br />implementing a simple urban bus routing prototype**

**Rationale:** This project is a *direct* **[Clojure](https://clojure.org "Lisp-1 dialect for the JVM")** port of the earlier developed **urban bus routing prototype**, written in Java using **[Spring Boot](https://spring.io/projects/spring-boot "Stand-alone Spring apps builder and runner")** framework, and tailored to be run as a microservice in a Docker container. The following description of the underlying architecture and logics has been taken **[from there](https://github.com/rgolubtsov/transroutownish-proto-bus-spring-boot)** as is, without any modifications or adjustment.

Consider an IoT system that aimed at planning and forming a specific bus route for a hypothetical passenger. One crucial part of such system is a **module**, that is responsible for filtering bus routes between two arbitrary bus stops where a direct route is actually present and can be easily found. Imagine there is a fictional urban public transportation agency that provides a wide series of bus routes, which covered large city areas, such that they are consisting of many bus stop points in each route. Let's name this agency **Trans-RoutE-Townish Co., Ltd.** or in the Net representation &mdash; **transroutownish.com**, hence the name of the project.

A **module** that is developed here is dedicated to find out quickly, whether there is a direct route in a list of given bus routes between two specified bus stops. It should immediately report back to the IoT system with the result `true` if such a route is found, i.e. it exists in the bus routes list, or `false` otherwise, by outputting a simple JSON structure using the following format:

```
{
    "from"   : <starting_bus_stop_point>,
    "to"     : <ending_bus_stop_point>,
    "direct" : true
}
```

`<starting_bus_stop_point>` and `<ending_bus_stop_point>` above are bus stop IDs: unique positive integers, taken right from inputs.

A bus routes list is a plain text file where each route has its own unique ID (positive integer) and a sequence of its bus stop IDs. Each route occupies only one line in this file, so that they are all representing something similar to a list &mdash; the list of routes. The first number in a route is always its own ID. Other consequent numbers after it are simply IDs of bus stops in this route, up to the end of line. All IDs in each route are separated by whitespace, usually by single spaces or tabs, but not newline.

There are some constraints:
1. Routes are considered not to be a round trip journey, that is they are operated in the forward direction only.
2. All IDs (of routes and bus stops) must be represented by positive integer values, in the range `1 .. 2,147,483,647`.
3. Any bus stop ID may occure in the current route only once, but it might be presented in any other route too.

The list of routes is usually mentioned throughout the source code as a **routes data store**, and a sample routes data store can be found in the `data/` directory of this repo.

Since the microservice architecture for building independent backend modules of a composite system are very prevalent nowadays, this seems to be natural for creating a microservice, which is containerized and run as a daemon, serving a continuous flow of HTTP requests.

This microservice is intended to be built locally and to be run like a conventional daemon in the VM environment, as well as a containerized service, managed by Docker.

One may consider this project has to be suitable for a wide variety of applied areas and may use this prototype as: (1) a template for building a similar microservice, (2) for evolving it to make something more universal, or (3) to simply explore it and take out some snippets and techniques from it for *educational purposes*, etc.

---

## Table of Contents

* **[Building](#building)**
  * **[Creating a Docker image](#creating-a-docker-image)**
* **[Running](#running)**
  * **[Running a Docker image](#running-a-docker-image)**
  * **[Exploring a Docker image payload](#exploring-a-docker-image-payload)**
* **[Consuming](#consuming)**
  * **[Logging](#logging)**
  * **[Error handling](#error-handling)**

## Building

The microservice is known to be built and run successfully under **Ubuntu Server (Ubuntu 22.04.1 LTS x86-64)**. Install the necessary dependencies (`openjdk-17-jre-headless`, `clojure`, `leiningen`, `make`, `docker.io`):

```
$ sudo apt-get update && \
  sudo apt-get install openjdk-17-jre-headless clojure leiningen make docker.io -y
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

Or **build** the microservice using **GNU Make** (it covers the same **Leiningen** build workflow under the hood, plus prepares the final all-in-one JAR file for further Docker containerization &mdash; this is preferred and encouraged build variant):

```
$ make clean
...
$ make      # <== Compilation phase. (Be aware: classes produced might be overwritten by the jar target.)
...
$ make tests
...
$ make jar
...
$ make all  # <== This is equivalent to the jar target.
...
```

### Creating a Docker image

**Build** a Docker image for the microservice:

```
$ # Pull the JRE image first, if not already there:
$ sudo docker pull azul/zulu-openjdk-alpine:11-jre-headless-latest
...
$ # Then build the microservice image:
$ sudo docker build -ttransroutownish/busclj .
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
$ java -jar target/uberjar/bus-0.15.2.jar; echo $?
...
```

### Running a Docker image

**Run** a Docker image of the microservice, deleting all stopped containers prior to that:

```
$ sudo docker rm `sudo docker ps -aq`; \
  export PORT=8765 && sudo docker run -dp${PORT}:${PORT} --name busclj transroutownish/busclj; echo $?
...
```

### Exploring a Docker image payload

The following is not necessary but might be considered interesting &mdash; to look up into the running container, and check out that the microservice's all-in-one JAR file, log, and routes data store are at their expected places and in effect:

```
$ sudo docker ps -a
CONTAINER ID   IMAGE                    COMMAND               CREATED             STATUS             PORTS                                       NAMES
<container_id> transroutownish/busclj   "java -jar bus.jar"   About an hour ago   Up About an hour   0.0.0.0:8765->8765/tcp, :::8765->8765/tcp   busclj
$
$ sudo docker exec -it busclj sh; echo $?
/var/tmp $
/var/tmp $ java --version
openjdk 11.0.17 2022-10-18 LTS
OpenJDK Runtime Environment Zulu11.60+19-CA (build 11.0.17+8-LTS)
OpenJDK 64-Bit Server VM Zulu11.60+19-CA (build 11.0.17+8-LTS, mixed mode)
/var/tmp $
/var/tmp $ ls -al
total 8740
drwxrwxrwt    1 root     root          4096 Nov 24 19:05 .
drwxr-xr-x    1 root     root          4096 Aug  9 08:58 ..
-rw-rw-r--    1 root     root       8928455 Nov 24 18:00 bus.jar
drwxr-xr-x    2 root     root          4096 Nov 24 19:00 data
drwxr-xr-x    2 daemon   daemon        4096 Nov 24 19:05 log
/var/tmp $
/var/tmp $ ls -al data/ log/
data/:
total 56
drwxr-xr-x    2 root     root          4096 Nov 24 19:00 .
drwxrwxrwt    1 root     root          4096 Nov 24 19:05 ..
-rw-rw-r--    1 root     root         46218 Jan 29  2022 routes.txt

log/:
total 12
drwxr-xr-x    2 daemon   daemon        4096 Nov 24 19:05 .
drwxrwxrwt    1 root     root          4096 Nov 24 19:05 ..
-rw-r--r--    1 daemon   daemon          59 Nov 24 19:05 bus.log
/var/tmp $
/var/tmp $ netstat -plunt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 0.0.0.0:8765            0.0.0.0:*               LISTEN      1/java
/var/tmp $
/var/tmp $ ps ax
PID   USER     TIME  COMMAND
    1 daemon    0:08 java -jar bus.jar
   22 daemon    0:00 sh
   42 daemon    0:00 ps ax
/var/tmp $
/var/tmp $ exit # Or simply <Ctrl-D>.
0
```

## Consuming

All the routes are contained in a so-called **routes data store**. It is located in the `data/` directory. The default filename for it is `routes.txt`, but it can be specified explicitly (if intended to use another one) in the `resources/settings.edn` file.

**Identify**, whether there is a direct route between two bus stops with IDs given in the **HTTP GET** request, searching for them against the underlying **routes data store**:

HTTP request param | Sample value | Another sample value | Yet another sample value
------------------ | ------------ | -------------------- | ------------------------
`from`             | `4838`       | `82`                 | `2147483647`
`to`               | `524987`     | `35390`              | `1`

The direct route is found:

```
$ curl 'http://localhost:8765/route/direct?from=4838&to=524987'
{"direct":true,"from":4838,"to":524987}
```

The direct route is not found:

```
$ curl 'http://localhost:8765/route/direct?from=82&to=35390'
{"direct":false,"from":82,"to":35390}
```

### Logging

The microservice has the ability to log messages to a logfile and to the Unix syslog facility. When running directly under Ubuntu Server (not in a Docker container), logs can be seen and analyzed in an ordinary fashion, by `tail`ing the `log/bus.log` logfile:

```
$ tail -f log/bus.log
...
[2022-04-10][20:10:30][INFO ]  Server started on port 8765
[2022-04-10][20:12:20][DEBUG]  from=4838 | to=524987
[2022-04-10][20:13:10][DEBUG]  from=82 | to=35390
[2022-04-10][20:15:00][INFO ]  Server stopped
```

Messages registered by the Unix system logger can be seen and analyzed using the `journalctl` utility:

```
$ journalctl -f
...
Apr 10 20:10:30 <hostname> java[<pid>]: Server started on port 8765
Apr 10 20:12:20 <hostname> java[<pid>]: from=4838 | to=524987
Apr 10 20:13:10 <hostname> java[<pid>]: from=82 | to=35390
Apr 10 20:15:00 <hostname> java[<pid>]: Server stopped
```

Inside the running container logs might be queried also by `tail`ing the `log/bus.log` logfile:

```
/var/tmp $ tail -f log/bus.log
[2022-04-10][20:20:10][INFO ]  Server started on port 8765
[2022-04-10][20:20:30][DEBUG]  from=4838 | to=524987
[2022-04-10][20:20:40][DEBUG]  from=82 | to=35390
```

And of course Docker itself gives the possibility to read log messages by using the corresponding command for that:

```
$ sudo docker logs -f busclj
[2022-04-10][20:20:10][INFO ]  Server started on port 8765
[2022-04-10][20:20:30][DEBUG]  from=4838 | to=524987
[2022-04-10][20:20:40][DEBUG]  from=82 | to=35390
```

### Error handling

When the query string passed in a request, contains inappropriate input, or the URI endpoint doesn't contain anything else at all after its path, the microservice will respond with the **HTTP 400 Bad Request** status code, including a specific response body in JSON representation, like the following:

```
$ curl 'http://localhost:8765/route/direct?from=qwerty4838&to=-i-.;--089asdf../nj524987'
{"error":"Request parameters must take positive integer values, in the range 1 .. 2,147,483,647. Please check your inputs."}
```

Or even simpler:

```
$ curl http://localhost:8765/route/direct
{"error":"Request parameters must take positive integer values, in the range 1 .. 2,147,483,647. Please check your inputs."}
```
