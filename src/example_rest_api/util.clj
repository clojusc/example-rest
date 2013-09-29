(ns example-rest-api.util
  (:require [clojure.tools.cli :as cli])
  (:import [java.util Date]))


(defn parse-options [args]
  (cli/cli args
    ["-h" "--help" "Show Help"
      :default false :flag true]
    ["-p" "--port" "Port to listen to"
      :default 8080 :parse-fn #(Integer. %)]
    ["-d" "--development" "Run server in development mode"
      :default false :flag true]))

(defn in-dev? [options]
  (options :development))

(defn show-help? [options]
  (options :help))

(defn get-port [options]
  (options :port))

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
