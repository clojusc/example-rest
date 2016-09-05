(ns example-rest.server.routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [example-rest.server.api :as api]))

(defroutes api-routes
  (GET "/" [] (api/hello-world))
  (route/resources "/")
  (route/not-found "Nope. Not here."))
