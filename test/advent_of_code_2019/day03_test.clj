(ns advent-of-code-2019.day03-test
  (:require [clojure.test :refer [is testing deftest run-tests]]
            [advent-of-code-2019.day03 :refer [solve load-data]]))

(deftest example-inputs-test
  (testing "The example inputs return expected values"
    ;; (is (= 159 (solve (load-data "./data/day03_test1.txt"))))
    ;; (is (= 135 (solve (load-data "./data/day03_test2.txt"))))
    (is (= 6 (solve (load-data "./data/day03_test3.txt"))))))


(run-tests)
