(ns example-rest.client
  (:require [clojure.tools.logging :as log]
            [clj-http.client :as http])
  (:import [java.net ConnectException]
           [java.io EOFException]))

(defn error-catcher [func]
  (try (func)
    (catch ConnectException error
      (log/error (.getMessage error)))
    (catch EOFException error
      (log/error (.getMessage error)))))

(defn get-body [data]
  (data :body))

(defn get-hello [base-url]
  (error-catcher
    #(get-body
      (http/get (str base-url "/")))))
