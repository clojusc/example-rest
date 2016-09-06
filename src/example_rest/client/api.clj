(ns example-rest.client.api
  (:require [example-rest.client.core :as http]))

(defn get-orders [base-url]
  (http/get (format "%s/orders" base-url)))

(defn get-order [base-url order-id]
  (http/get (format "%s/order/%s" base-url order-id)))
