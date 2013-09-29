(ns example-rest-api.config-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [example-rest-api.config :as config]
            [example-rest-api.handler :as handler]))


(deftest test-default-values
    (is (= 8080 config/default-port)))
