(ns example-rest-api.client
  (:require [clojure.tools.logging :as log]
            [clj-http.client :as http])
  (:import [java.net ConnectException]
           [java.io EOFException]))


(defn get-hello [base-url]
  (try ((http/get (str base-url "/")) :body)
    (catch ConnectException error
      (log/error (.getMessage error)))
    (catch EOFException error
      (log/error (.getMessage error)))))

