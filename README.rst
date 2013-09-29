####################
REST APIs in Clojure
####################

*An Example*

Purpose
=======

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


What Next?
==========

What should I look at first, if I'm a new Clojure web developer?

Well, you should definitely ready the rest of this README. Then you might want
to check out the following, probably in order:

* src/example-rest-api/routes.clj
* src/example-rest-api/api.clj
* src/example-rest-api/client.clj
* scripts/get-hello


Anatomy of this REST Project
============================

Components
----------

REST API:
 * compojure

SQL and Databases:
 * clojure.contrib.sql and korma
 * monger

Caching:
 * spyglass

Client Library:
 * clj-http-client


Layout
------

TBD


Development
===========

From the command line:

TBD

From the REPL:

TBD


Creating Project Tools
======================

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

.. code:: shell

    $ ./scripts/get-hello -h

     Switches               Default                Desc
     --------               -------                ----
     -h, --no-help, --help  false                  Show Help
     -b, --base-url         http://localhost:8080  Base URL of the REST API server


Deployment
==========

The `Makefile` gives an example of doing standalone deployment with the
`standalone` target:

.. code:: shell

    java -jar  target/example-rest-api-0.1.0-SNAPSHOT-standalone.jar

That `.jar` file is built with the following command:

.. code:: shell

    make build

Which, in turn, calls the following to build the standalone `.jar` file:

.. code:: shell

    lein compile
    lein uberjar


Testing
=======

Libraries used:
 * ring-mock
 * midje
 * clj-http-fake
 * lein-kibit
 * lein cloverage

To use kibit, cloverage, and midje from lein, you'll want to update your
`~/.lein/profiles.clj" with something like the following:

.. code:: clojure

  {:user
    {:plugins
      [[lein-kibit "0.0.8"]
       [lein-cloverage "1.0.2"]
       [lein-midje "3.0.0"]]}}
