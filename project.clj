(defproject example-rest-api "0.1.0-SNAPSHOT"
  :description "An Example REST API Server for Clojure"
  :url "http://localhost/example-api"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]]
  :plugins [[lein-ring "0.8.5"]
            [lein-exec "0.3.1"]]
  :ring {:handler example-rest-api.handler/app}
  :profiles
  {:testing
    {:dependencies [[ring-mock "0.1.5"]
                    [clj-http-fake "0.4.1"]
                    [midje "1.5.1"]]}})
