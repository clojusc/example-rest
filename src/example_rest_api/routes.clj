(ns example-rest-api.routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [example-rest-api.const :as const]))


(defroutes api-routes
  (GET "/" [] "Hello World!!!")
  (route/resources "/")
  (route/not-found const/four-oh-four-message))
