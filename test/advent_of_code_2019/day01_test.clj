(ns advent-of-code-2019.day01-test
  (:require [advent-of-code-2019.day01 :refer [total-fuel]]
            [clojure.test :refer [deftest testing are run-tests]]))

(deftest total-fuel-test
  (testing "Example values"
    (are [x y] (= x (total-fuel y))
      2 14
      966 1969
      50346 100756)))

(run-tests)
