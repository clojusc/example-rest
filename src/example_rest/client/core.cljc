(ns example-rest.client.core
  (:require [clojure.tools.logging :as log]
            [clj-http.client :as http]
            [example-rest.util :as util])
  (:import [java.net ConnectException]
           [java.io EOFException])
  (:refer-clojure :exclude [get]))

(defn error-catcher [func]
  (try (func)
    (catch ConnectException error
      (log/error (.getMessage error)))
    (catch EOFException error
      (log/error (.getMessage error)))))

(defn get-http-func [method]
  #?(:clj (case method
            :get #'http/get
            :head #'http/head
            :post #'http/post
            :put #'http/put
            :delete #'http/delete
            :options #'http/options
            :copy #'http/copy
            :move #'http/move
            :patch #'http/patch)
     :cljs (case method
             :get #'http/get
             :head #'http/head
             :post #'http/post
             :put #'http/put
             :delete #'http/delete
             :options #'http/options
             :move #'http/move
             :patch #'http/patch)))

(defn http-call [method path args]
  (log/infof "method, path, args: [%s %s %s]" method path args)
  (let [result (apply (get-http-func method) (into [method path] args))]
    (log/info "Got result:" result)
    result))

(defn get [path & args]
  (http-call :get path args))

(defn head [path & args]
  (http-call :head path args))

(defn post [path & args]
  (http-call :post path args))

(defn put [path & args]
  (http-call :put path args))

(defn delete [path & args]
  (http-call :delete path args))

(defn options [path & args]
  (http-call :options path args))

(defn copy [path & args]
  (http-call :copy path args))

(defn move [path & args]
  (http-call :move path args))

(defn patch [path & args]
  (http-call :patch path args))

(util/add-error-handler
  #'http-call
  [:status 404]
  :not-found
  404)
