(ns example-rest-api.main
  (:require [clojure.tools.logging :as log]
            [org.httpkit.server :as httpkit]
            [compojure.handler :refer [site]]
            [example-rest.server.routes :as routes])
  (:gen-class))

(defn -main
  "This is the entry point.
  'lein run' will use this as well as 'java -jar'."
  [& args]
  (log/info "Starting Compojure server ...")
  (httpkit/run-server (site #'routes/v1) {:port 8080}))
