# REST Server in Clojure

[![Build Status][travis-badge]][travis][![Dependencies Status][deps-badge]][deps][![Clojars Project][clojars-badge]][clojars][![Clojure version][clojure-v]](project.clj)

*An Example*

[![][logo]][logo-large]


##### Contents

* [Background](#background-)
* [About](#about-)
  * [I'm New!](#im-new-)
  * [Anatomy](#anatomy-)
    * [Layout](#layout-)
    * [Server](#server-)
    * [Client](#client-)
    * [CLI](#cli-)
* [Development](#development-)
* [Testing](#license-)
* [Deployment](#license-)
* [License](#license-)


## Background [&#x219F;](#contents)

This project started as a resources for engineers who were learning Clojure
during Hack Day events at work. For those who were new to Clojure, we wanted
to offer an example project that provided insight on ways one can do the
following:

* Define functions that comprise the REST server
* Define routes that call the REST API functions (separating HTTP-spefific
  code from "business logic" code)
* Configuration, logging, and database components
* Have functions that access a data source
* Provide a client library for the REST API
* Provide executable scripts that use the client library
* Include unit tests, functional tests, and style checking/linting
* Provide a means of running code in development mode locally
* Provide standalone .jar files for deplopyment into production

Since then, this has become a resource for seasoned devs whose Clojure REST
chops are a bit rusty, and just need to get a jump-start for a new REST
project.


## About [&#x219F;](#contents)

### I'm New! [&#x219F;](#contents)

What should I look at first, if I'm a new Clojure web developer?

Well, you should definitely ready the rest of this README. Then you might want
to check out the following, probably in order:

* `src/example-rest/server/routes.clj`
* `src/example-rest/server/api.clj`
* `src/example-rest/client/core.clj`
* `scripts/get-hello`


### Anatomy [&#x219F;](#contents)

... Of this REST Project


#### Layout [&#x219F;](#contents)

TBD


#### Server [&#x219F;](#contents)

TBD


#### Client [&#x219F;](#contents)

TBD


#### CLI [&#x219F;](#contents)

[This section is showing all of its three years of age ... updates pending]

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


## Development [&#x219F;](#contents)

From the command line:

TBD

From the REPL:

TBD


## Testing [&#x219F;](#contents)

Libraries used:
 * clojure.test
 * lein-kibit
 * eastwood


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


## License [&#x219F;](#contents)

Copyright © 2013-2016 Duncan McGreggor

Distributed under the Apache License Version 2.0.


<!-- Named page links below: /-->

[travis]: https://travis-ci.org/clojusc/example-rest-server
[travis-badge]: https://travis-ci.org/clojusc/example-rest-server.png?branch=master
[deps]: http://jarkeeper.com/clojusc/example-rest-server
[deps-badge]: http://jarkeeper.com/clojusc/example-rest-server/status.svg
[logo]: https://avatars1.githubusercontent.com/u/18177940?v=3&s=250
[logo-large]: https://avatars1.githubusercontent.com/u/18177940?v=3&s=1000
[tag-badge]: https://img.shields.io/github/tag/clojusc/example-rest-server.svg?maxAge=2592000
[tag]: https://github.com/clojusc/example-rest-server/tags
[clojure-v]: https://img.shields.io/badge/clojure-1.8.0-blue.svg
[clojars]: https://clojars.org/clojusc/example-rest-server
[clojars-badge]: https://img.shields.io/clojars/v/clojusc/example-rest-server.svg

