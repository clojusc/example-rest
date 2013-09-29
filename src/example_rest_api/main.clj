(ns example-rest-api.main
  (:require [clojure.tools.logging :as log]
            [clj-logging-config.log4j :as log-config]
            [example-rest-api.config :as config]
            [example-rest-api.server :as server]
            [example-rest-api.util :as util])
  (:gen-class))


(log-config/set-logger!
  "Example REST API Logger"
  :name "api-log"
  :pattern "%d - %m%n"
  :out (org.apache.log4j.ConsoleAppender.))

(defn -main
  "This is the entry point.

  'lein run' will use this as well as 'java -jar'."
  [& args]
  (let [[options args banner] (util/parse-options args)
        server-manager (server/new-manager options)]
    (when (util/show-help? options)
      (println banner)
      (System/exit 0))

    (if (util/in-dev? options)
       (server/start-dev server-manager :port config/default-port)
      (server/start server-manager))))
