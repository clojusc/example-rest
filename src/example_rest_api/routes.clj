(ns example-rest-api.routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [example-rest-api.api :as api]
            [example-rest-api.const :as const]))


(defroutes api-routes
  (GET "/" [] (api/hello-world))
  (route/resources "/")
  (route/not-found const/four-oh-four-message))
