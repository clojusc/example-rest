(ns example-rest.client.api
  (:require [example-rest.client.core :as http]))

(defn get-orders [base-url & args]
  (http/get (format "%s/orders" base-url) args))

(defn get-order [base-url order-id & args]
  (http/get (format "%s/order/%s" base-url order-id) args))

(defn new-order [base-url & args]
  (http/post (format "%s/order" base-url) args))

(defn update-order [base-url order-id status & args]
  (http/put
    (format "%s/order/%s/%s" base-url order-id (name status)) args))

(defn cancel-order [base-url order-id & args]
  (http/delete (format "%s/order/%s" base-url order-id) args))

(defn get-order-options [base-url order-id & args]
  (http/options (format "%s/order/%s" base-url order-id) args))

(defn update-payment [base-url order-id status & args]
  (http/put
    (format "%s/payment/order/%s/%s" base-url order-id (name status)) args))

(defn get-payment [base-url order-id & args]
  (http/get (format "%s/payment/order/%s" base-url order-id) args))

(defn get-payment-options [base-url order-id & args]
  (http/options (format "%s/payment/order/%s" base-url order-id) args))
