(ns example-rest-api.handler
  (:require [compojure.handler :as handler]
            [ring.middleware.reload :as reload]
            [example-rest-api.routes :as routes]
            [example-rest-api.util :as util]))


(defn get-app
  ""
  ([]
    (get-app {}))
  ([options]
    (if (util/in-dev? options)
      (reload/wrap-reload (handler/site #'routes/api-routes))
      (handler/site routes/api-routes))))
