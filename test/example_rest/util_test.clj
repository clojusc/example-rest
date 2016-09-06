(ns example-rest.util-test
  (:require [clojure.test :refer :all]
            [example-rest.util :as util]))

(deftest ^:unit get-message
  (is (= "Wheeee!" (util/get-message (Exception. "Wheeee!")))))
