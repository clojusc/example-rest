(ns example-rest.server.components
  (:require [com.stuartsierra.component :as component]
            [example-rest.server.components.config :as config]
            [example-rest.server.components.logger :as logger]
            [example-rest.server.components.httpd :as httpd]
            [example-rest.server.components.system :as system]))

(defn init [app]
  (component/system-map
    :cfg (config/new-configuration)
    :logger (component/using
              (logger/new-logger)
              [:cfg])
    :httpd (component/using
             (httpd/new-server app)
             [:cfg
              :logger])
    :sys (component/using
           (system/new-toplevel-system)
           [:cfg
            :logger
            :httpd])))

(defn stop [system component-key]
  (->> system
       (component-key)
       (component/stop)
       (assoc system component-key)))

(defn start [system component-key]
  (->> system
       (component-key)
       (component/start)
       (assoc system component-key)))

(defn restart [system component-key]
  (-> system
      (stop component-key)
      (start component-key)))
