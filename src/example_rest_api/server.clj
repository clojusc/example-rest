(ns example-rest-api.server
  (:require [clojure.repl :as repl]
            [clojure.tools.logging :as log]
            [org.httpkit.server :as httpkit]
            [example-rest-api.config :as config]
            [example-rest-api.handler :as handler]
            [example-rest-api.util :as util]))


(defn -start-server
  "Run an HTTP server. This is what ends up actually calling the http-kit
  run-server function.

  The 'options' parameter is just a map. To run a dev server, the minimal
  otions necessary are:
   {:development true}"
  [options]
  (let [app (handler/get-app options)
        port (util/get-port options)]
    (httpkit/run-server app {:port port})))

(defn -start-dev-server
  "Run a dev HTTP server on the default port."
  [port]
  (-start-server {:development true :port port}))

(defn new-manager [& [options server]]
  "This is an object that uses closures to keep track of server options and a
  server instance (as ulimately returned by httpkit/run-server).

  The use of nested anonymous functions for tracking state is somewhat abstruse.
  However, the API for http-kit isn't such a good one, in this case. You likely
  won't need to call this function ever, since convenience functions are
  provided below. In the event that you wanted to use this function directly
  from the REPL, you would do something like this:
    => (def server-manager (new-manager))
    => (def server-manager (start server-manager))
    => (stop server-manager)
  "
  (fn [command]
    (case command
      :start (fn []
               (log/info "Running server on port" config/default-port)
               (new-manager
                 options
                 (-start-server options)))
      :start-dev (fn [port]
                   (log/info "Running development server on port" port)
                   (new-manager
                     {:port port}
                     (-start-dev-server port)))
      :stop (fn []
              (log/info "Stopping server")
              ;(server)
              (System/exit 0)))))

(defn -do-server-action [server command]
  "This is used as a convenience function in the following functions to avoid
  typing multiple 'apply's for every function that needs to call into the server
  manager."
  (apply server [command]))

(defn stop [server]
  "A function for stoping a server.

  The intent is to use this from the REPL:
    => (def server (run-dev))
    => (stop server)
  "
  (apply (-do-server-action server :stop) []))

(defn start [server]
  "A function for starting a server.

  Generally, a regular server will be started via the command-line with
  command-line options. As such, no options are provided for this function."
  (repl/set-break-handler! (fn [_] (stop server)))
  (apply (-do-server-action server :start) []))

(defn start-dev [server & {:keys [port] :or {:port config/default-port}}]
  "A function for starting a development server with an optional :port keyword."
  (repl/set-break-handler! (fn [_] (stop server)))
  (apply (-do-server-action server :start-dev) [port]))

(defn run []
  "Run a regular server. Any changes made to files while running this server
  will not be apparent until the server is restarted.

  The intent is to use this from the REPL:
    => (def server (run))
  "
  (let [server (new-manager {:port config/default-port})]
    (start server)))

(defn run-dev
  "Run a development server, with an optional :port keyword. This server will
  start an http-kit server running in development (autoreload) mode. As such,
  any changes to files will result in corresponding changes to the running
  server.

  The intent is to use this from the REPL:
    => (def server (run-dev))
    => (def server (run-dev 7080))
  "
  ([]
    (run-dev config/default-port))
  ([port]
    (let [server (new-manager)]
      (start-dev server :port port))))
