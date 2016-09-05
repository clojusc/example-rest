(ns example-rest.server.components.logger
  (:require [clojure.tools.logging :as log]
            [clojure.tools.logging.impl :as log-impl]
            [com.stuartsierra.component :as component]
            [clojusc.twig :as logger]))

(defn get-log-level
  "A convenience function for use in the Logger component."
  [component]
  (->> [:cfg :logging :level]
       (get-in component)
       (keyword)))

(defn get-namespaces
  "A convenience function for use in the Logger component."
  [component]
  (->> [:cfg :logging :namespaces]
       (get-in component)
       (map symbol)
       (into [])))

(defrecord Logger []
  component/Lifecycle

  (start [component]
    (log/info "Setting up REST logging ...")
    (let [log-level (get-log-level component)
          namespaces (get-namespaces component)]
      (log/info "Using log-level" log-level)
      (logger/set-level! namespaces log-level)
      (log/trace "Logging agent:" log/*logging-agent*)
      (log/trace "Logging factory:" (logger/get-factory))
      (log/trace "Logging factory name:" (logger/get-factory-name))
      (log/debug "Logger:" (logger/get-logger *ns*))
      (log/debug "Logger name:" (logger/get-logger-name *ns*))
      (log/debug "Logger level:" (logger/get-logger-level *ns*))
      (log/trace "Logger context:" (logger/get-logger-context *ns*))
      (log/trace "Logger configurator:" (logger/get-config *ns*))
      (log/debug "Set log level for these namespaces:" namespaces)
      (log/debug "Successfully configured logging.")
      component))

  (stop [component]
    (log/info "Tearing down REST Logging ...")
    (log/debug "Component keys" (keys component))
    component))

(defn new-logger []
  (->Logger))
