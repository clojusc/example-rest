(ns example-rest.cli.core
  (:require [clojure.tools.cli :as cli]))

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
