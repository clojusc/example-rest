# REST Server in Clojure

[![Build Status][travis-badge]][travis][![Dependencies Status][deps-badge]][deps][![Clojars Project][clojars-badge]][clojars][![Clojure version][clojure-v]](project.clj)

*An Example*

[![][logo]][logo-large]


##### Contents

* [Purpose](#purpose-)
* [I'm New!](#im-new-)
* [Anatomy](#anatomy-)
  * [Components](#Components-)
  * [Layout](#layout-)
* [Development](#development-)
* [CLI](#cli-)
* [Deployment](#license-)
* [Testing](#license-)
* [License](#license-)


## Purpose [&#x219F;](#contents)

For those new to Clojure, we wanted to offer an example project that provides
insight on ways one can do the following in a REST API:

* Define functions that comprise the REST API
* Define routes that call the REST API functions
* Have functions that access databases
* Have functions with expensive calls whose results get cached
* Configure logging
* Provide a client library for the REST API
* Provide executable scripts that use the client library
* Include unit tests, functional tests, style checking, and test coverag
* Provide a means of running code in development mode locally
* Provide standalone .jar files for deplopyment into production


## I'm New! [&#x219F;](#contents)

What should I look at first, if I'm a new Clojure web developer?

Well, you should definitely ready the rest of this README. Then you might want
to check out the following, probably in order:

* src/example-rest-api/routes.clj
* src/example-rest-api/api.clj
* src/example-rest-api/client.clj
* scripts/get-hello


## Anatomy [&#x219F;](#contents)

... Of this REST Project

### Components [&#x219F;](#contents)

TBD

### Layout [&#x219F;](#contents)

TBD


## Development [&#x219F;](#contents)

From the command line:

TBD

From the REPL:

TBD


## CLI [&#x219F;](#contents)

*Creating Project Tools ...*

It's always nice to deliver an API that already has at least one client library
available, so that users are not forced to resort to `curl` and all the crazy,
long command line arguments that come with that. With an API defined, creating
a client library is very easy to do.

The next step in creating a good experience for your future users is to create
some tools based upon your client libraries. In particular, command line tools
are an excellent step in that direction. The `scripts` directory gives some
examples of this, making use of the `lein-exec` plugin. Another option would be
to create standalone jar files for each of your scripts. That entails more work
up front, but results in faster load times of the executable.

The example scripts provide command line arguments and options parsring:

```
$ ./scripts/get-hello -h

 Switches               Default                Desc
 --------               -------                ----
 -h, --no-help, --help  false                  Show Help
 -b, --base-url         http://localhost:8080  Base URL of the REST API server
```

## Deployment [&#x219F;](#contents)

The `Makefile` gives an example of doing standalone deployment with the
`standalone` target:

```
$ java -jar  target/example-rest-api-0.1.0-SNAPSHOT-standalone.jar
```

That `.jar` file is built with the following command:

```
$ make build
```

Which, in turn, calls the following to build the standalone `.jar` file:

```
$ lein compile
$ lein uberjar
```

## Testing [&#x219F;](#contents)

Libraries used:
 * clojure.test
 * lein-kibit
 * eastwood


## License [&#x219F;](#contents)

Copyright © 2013-2016 Duncan McGreggor

Distributed under the Apache License Version 2.0.


<!-- Named page links below: /-->

[travis]: https://travis-ci.org/clojusc/example-rest-server
[travis-badge]: https://travis-ci.org/clojusc/example-rest-server.png?branch=master
[deps]: http://jarkeeper.com/clojusc/example-rest-server
[deps-badge]: http://jarkeeper.com/clojusc/example-rest-server/status.svg
[logo]: resources/images/logo.png
[logo-large]: resources/images/logo-large.png
[tag-badge]: https://img.shields.io/github/tag/clojusc/example-rest-server.svg?maxAge=2592000
[tag]: https://github.com/clojusc/example-rest-server/tags
[clojure-v]: https://img.shields.io/badge/clojure-1.8.0-blue.svg
[clojars]: https://clojars.org/clojusc/example-rest-server
[clojars-badge]: https://img.shields.io/clojars/v/clojusc/example-rest-server.svg

