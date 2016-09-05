(ns example-rest.server.components.httpd
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [org.httpkit.server :as httpkit]))

(defn inject-app
  "Make app components available to request handlers.

  Note that even though it is called ``:component``, the association performed
  in this function is for the ``:httpd`` component as defined in
  ``example-rest.server.components``. This is done for the sake of clarity in
  other parts of the example-rest.server codebase where ``:httpd`` might be
  ambigious and``:component`` is a better symmantic fit."
  [handler component]
  (fn [request]
    (handler (assoc request :component component))))

(defrecord HTTPServer [ring-handler]
  component/Lifecycle

  (start [component]
    (log/info "Starting HTTP server ...")
    (let [http-cfg (get-in component [:cfg :rest-server])
          handler (inject-app ring-handler component)
          server (httpkit/run-server handler http-cfg)]
      (log/infof "Started HTTP server on %s:%s"
                 (:host http-cfg) (:port http-cfg))
      (log/debug "Using config:" http-cfg)
      (log/debug "Component keys:" (keys component))
      (log/debug "Successfully created server:" server)
      (assoc component :httpd server)))

  (stop [component]
    (log/info "Stopping HTTP server ...")
    (log/debug "Component keys" (keys component))
    (if-let [server (:httpd component)]
      (do (log/debug "Using server object:" server)
          (server))) ; calling server like this stops it, if started
    (assoc component :httpd nil)))

(defn new-server [ring-handler]
  (->HTTPServer ring-handler))
