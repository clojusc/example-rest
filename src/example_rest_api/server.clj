(ns example-rest-api.server
  (:require [clojure.tools.logging :as log]
            [org.httpkit.server :as httpkit]
            [example-rest-api.config :as config]
            [example-rest-api.handler :as handler]
            [example-rest-api.util :as util]))


(defn -start-server
  "Run an HTTP server. This is useful in the REPL.

  The 'options' parameter is just a map. To run a dev server, the minimal
  otions necessary are:
   {:development true}"
  [options]
  (let [app-handler (handler/get-handler options)
        port (util/get-port options)]
    (log/info "Running server on port" port)
    (if (util/in-dev? options)
      (log/info "Server is in development mode."))
    (httpkit/run-server app-handler {:port port})))

(defn -start-dev-server
  "Run a dev HTTP server on the default port."
  [port]
  (-start-server {:development true :port port}))

(defn new-manager [& [options server]]
  ""
  (fn [command]
    (case command
      :start (fn []
               (new-manager
                 options
                 (-start-server options)))
      :start-dev (fn [port]
                   (new-manager
                     {:port port}
                     (-start-dev-server port)))
      :stop (fn []
              (new-manager
                options
                (server))))))

(defn do-server-action [server command]
  (apply server [command]))

(defn start [server]
  (apply (do-server-action server :start) []))

(defn start-dev [server & {:keys [port] :or {:port config/default-port}}]
  (apply (do-server-action server :start-dev) [port]))

(defn stop [server]
  (apply (do-server-action server :stop) []))

(defn run []
  (let [server (new-manager {:port config/default-port})]
    (start server)))

(defn run-dev
  ([]
    (run-dev config/default-port))
  ([port]
    (let [server (new-manager)]
      (start-dev server :port port))))
