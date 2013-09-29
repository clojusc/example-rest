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


Components
==========

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


Deployment
==========

TBD


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
