(ns example-rest-api.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.reload :as reload]
            [example-rest-api.const :as const]
            [example-rest-api.util :as util]))


(defroutes app-routes
  (GET "/" [] "Hello World!!!")
  (route/resources "/")
  (route/not-found const/four-oh-four-message))

(defn get-app
  ""
  ([]
    (get-app {}))
  ([options]
    (if (util/in-dev? options)
      (reload/wrap-reload (handler/site #'app-routes))
      (handler/site app-routes))))
