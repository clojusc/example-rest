(ns example-rest.client.core
  (:require [clojure.tools.logging :as log]
            [clj-http.client :as http]
            [example-rest.util :as util])
  (:import [java.net ConnectException]
           [java.io EOFException])
  (:refer-clojure :exclude [get]))

(defrecord ClientData [result debug errors])

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
  (log/tracef "method, path, args: [%s \"%s\" %s]" method path args)
  (let [[{debug :debug}] args
        func (get-http-func method)
        args (into [path] args)
        response (apply func args)
        data (ClientData. (:body response) nil nil)]
    (log/debug "Got result:" data)
    (if debug
      (assoc data :debug {:response response})
      data)))

(defn get
  ([path]
    (get path []))
  ([path args]
    (http-call :get path args)))

(defn head
  ([path]
    (head path []))
  ([path args]
    (http-call :head path args)))

(defn post
  ([path]
    (post path []))
  ([path args]
    (http-call :post path args)))

(defn put
  ([path]
    (put path []))
  ([path args]
    (http-call :put path args)))

(defn delete
  ([path]
    (delete path []))
  ([path args]
    (http-call :delete path args)))

(defn options
  ([path]
    (options path []))
  ([path args]
    (http-call :options path args)))

(defn copy
  ([path]
    (copy path []))
  ([path args]
    (http-call :copy path args)))

(defn move
  ([path]
    (move path []))
  ([path args]
    (http-call :move path args)))

(defn patch
  ([path]
    (patch path []))
  ([path args]
    (http-call :patch path args)))

(defn error-gen [ex args err-id err-status error-data]
  (ClientData. nil
               {:error-id err-id
                :status err-status
                :args args}
               [error-data]))

(util/add-error-handler
  #'http-call
  ConnectException
  :no-server-connection
  500
  #'error-gen)

(util/add-error-handler
  #'http-call
  [:status 404]
  :not-found
  404
  #'error-gen)
