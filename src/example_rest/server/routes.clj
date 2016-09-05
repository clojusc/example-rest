(ns example-rest.server.routes
  (:require [compojure.core :refer [GET OPTIONS POST PUT DELETE context defroutes]]
            [compojure.route :as route]
            [example-rest-api.management :as management]))

(defroutes v1
  (GET "/orders" [] "<h1>All Current Orders:</h1>")
  (POST "/order" [] "<h1>Order placed.</h1>")
  (GET "/order/:id" [id] (str "<h2>Order " id " is not ready.</h2>"))
  (PUT "/order/:id" [id] (str "<h2>Received update for Order " id "</h2>"))
  (DELETE "/order/:id" [id] (str "<h2>All done with Order " id "</h2>"))
  (OPTIONS "/order/:id" [id] (str "<h2>That order is allowed to...</h2>"))
  (GET "/payment/order/:id" [id] (str "<h2>Payment Status</h2>"))
  (PUT "/payment/order/:id" [id] (str "<h2>Paid for Order " id "</h2>"))
  (OPTIONS "/payment/order/:id" [id] "<h2>That payment can be...</h2>"))
