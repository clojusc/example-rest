######################################
An Example REST API Server for Clojure
######################################

Purpose
=======

TBD


Components
==========

REST API:
 * compojure
 * clojure.contrib.sql and korma
 * spyglass
 * monger

A note on the use of korma vs. clojure.contrib.sql:

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
