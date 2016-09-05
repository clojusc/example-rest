(ns example-rest.server.components.system
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defrecord RESTServerSystem []
  component/Lifecycle

  (start [component]
    (log/info "System dependencies started; finishing REST startup ...")
    ;; XXX add any start-up needed for system as a whole
    (log/debug "System startup complete.")
    component)

  (stop [component]
    (log/info "Shutting down top-level REST system ...")
    ;; XXX add any tear-down needed for system as a whole
    (log/debug "Top-level shutdown complete; shutting down system dependencies ...")
    component))

(defn new-toplevel-system []
  (->RESTServerSystem))
