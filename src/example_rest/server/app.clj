(ns example-rest.server.app
  (:require [clojure.tools.logging :as log]
            [ring.middleware.defaults :as ring-defaults]
            [ring.middleware.json :as ring-json]
            [ring.middleware.logger :as ring-logger]
            [com.stuartsierra.component :as component]
            [clojusc.twig :as logger]
            [example-rest.server.components :as components]
            [example-rest.server.routes :as routes]
            [example-rest.util :as util])
  (:gen-class))

(def app
  (-> routes/v1
      (ring-defaults/wrap-defaults ring-defaults/api-defaults)
      (ring-json/wrap-json-body {:keywords? true})
      (ring-logger/wrap-with-logger)))

(defn -main
  "This is the entry point. Note, however, that the system components are
  defined in example-rest.server.components. In particular,
  example-rest.server.components.system brings together all the defined (and
  active) components; that is the module which is used to bring the system up
  when (component/start ...) is called.

  'lein run' will use this as well as 'java -jar'."
  [& args]
  ;; Set the initial log-level before the components set the log-levels for
  ;; the configured namespaces
  (logger/set-level! ['example-rest] :info)
  (let [system (components/init #'app)]
    (component/start system)
    ;; Setup interrupt/terminate handling
    (util/add-shutdown-handler #(component/stop system))))
