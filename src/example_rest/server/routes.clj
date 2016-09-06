(ns example-rest.server.routes
  (:require [compojure.core :refer [GET OPTIONS POST PUT DELETE
                                    context defroutes]]
            [compojure.route :as route]))

(defroutes v1
  (GET "/orders" []
    "<h1>All Current Orders:</h1>")
  (POST "/order" []
    "<h1>Order placed.</h1>")
  (GET "/order/:id" [id]
    (str "<h2>Order " id " is not ready.</h2>"))
  (PUT "/order/:id/:status" [id status]
    (str "<h2>Order " id " is now " status "</h2>"))
  (DELETE "/order/:id" [id]
    (str "<h2>Order " id " has been cancelled.</h2>"))
  (OPTIONS "/order/:id" [id]
    (str "<h2>That order is allowed to...</h2>"))
  (GET "/payment/order/:id" [id]
    (str "<h2>Payment Status</h2>"))
  (PUT "/payment/order/:id/:status" [id status]
    (str "<h2>Status for Order " id ": " status " </h2>"))
  (OPTIONS "/payment/order/:id" [id]
    "<h2>That payment can be...</h2>"))
