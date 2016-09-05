(ns example-rest.server.handler
  (:require [compojure.handler :as handler]
            [ring.middleware.reload :as reload]
            [example-rest.server.routes :as routes]
            [example-rest.util :as util]))


(defn get-app
  ""
  ([]
    (get-app {}))
  ([options]
    (if (util/in-dev? options)
      (reload/wrap-reload (handler/site #'routes/api-routes))
      (handler/site routes/api-routes))))
