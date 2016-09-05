(ns example-rest.server.main
  (:require [clojure.tools.logging :as log]
            [example-rest.server.core :as server]
            [example-rest.util :as util])
  (:gen-class))

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
