(ns example-rest-api.const-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [example-rest-api.const :as const]
            [example-rest-api.handler :as handler]))


(deftest test-four-of-four-message
  (is (= 154 (count const/four-oh-four-message))))
