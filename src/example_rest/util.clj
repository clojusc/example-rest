(ns example-rest.util
  (:require [clojure.tools.logging :as log]
            [dire.core :refer [with-handler!]])
  (:import [java.util Date]))

(defn in?
  "This function returns true if the provided seqenuce contains the given
  elment."
  [sequence elm]
  (some #(= elm %) sequence))

(defn now []
  (Date.))

(defmulti strftime
  "Format time t according to format string fmt.

  Note that this was copied from Miki Tebeka's work here:
    https://github.com/tebeka/clj-strftime/blob/master/src/strftime.clj"
  (fn [fmt t] (class t)))

(defmethod strftime Date
  [fmt t]
  ; Convert strftime to String.format format (e.g. %m -> %1$tm)
  (let [fmt (.replaceAll fmt "%([a-zA-Z])" "%1\\$t$1")]
    (format fmt t)))

(defn get-local-ip
  "Get the IP address of the local machine."
  []
  (.getHostAddress (java.net.InetAddress/getLocalHost)))

(defn add-shutdown-handler [func]
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. func)))

(defn get-message [exception]
  (if (map? exception)
    exception
    (.getMessage exception)))

(defn process-error [exception id]
  (let [error-data {:id id}]
    (log/error id)
    (assoc error-data :detail (get-message exception))))

(defn add-error-handler
  ([func ex err-id err-status]
    (with-handler!
      func
      ex
      (fn [e & args]
        (process-error e err-id))))
  ([func ex err-id err-status error-func]
    (with-handler!
      func
      ex
      (fn [e & args]
        (error-func e args err-id err-status (process-error e err-id))))))
