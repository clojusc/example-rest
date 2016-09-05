(ns example-rest.server.components.config
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [leiningen.core.project :as lein-prj]))

(defrecord Configuration []
  component/Lifecycle

  (start [component]
    (log/info "Starting configuration component ...")
    (let [cfg-map (lein-prj/read)]
      (log/trace "Configuration data:" cfg-map)
      (merge component cfg-map)))

  (stop [component]
    (log/info "Stopping configuration component ...")))

(defn new-configuration []
  (log/debug "Building configuration component ...")
  (->Configuration))
