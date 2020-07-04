(ns practicalli.random-clojure-function-test
  (:require [clojure.test :refer [deftest is testing]]
            [practicalli.random-clojure-function :as SUT]))


(deftest -main-test
  (testing "Show random function from Clojure standard library"

    (is (seq SUT/standard-library-functions))

    (is (string? (SUT/-main)))))
