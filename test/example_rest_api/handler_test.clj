(ns example-rest-api.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [example-rest-api.handler :as handler]))


(deftest test-app
  (testing "main route"
    (let [response ((handler/get-app) (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World!!!"))))

  (testing "not-found route"
    (let [response ((handler/get-app) (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
