(ns example-rest-api.client
  (:require [clj-http.client :as http]))


(defn get-hello [base-url]
  ((http/get (str base-url "/")) :body))
