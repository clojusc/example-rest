(ns example-rest.client.api
  (:require [example-rest.client.core :as http]))

(defn get-orders [base-url & args]
  (http/get (format "%s/orders" base-url) args))

(defn get-order [base-url order-id & args]
  (http/get (format "%s/order/%s" base-url order-id) args))
