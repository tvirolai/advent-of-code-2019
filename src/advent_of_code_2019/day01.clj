(ns advent-of-code-2019.day01
  (:require [clojure.java.io :as io]))

(def data
  (with-open [rdr (io/reader "./data/day01.txt")]
    (->> (line-seq rdr)
         doall
         (map #(Integer/parseInt %)))))

(defn count-fuel [mass]
  (int (- (Math/floor (/ mass 3)) 2)))

(defn part-1 []
  (reduce + (map count-fuel data)))

(defn total-fuel [mass]
  (->> (iterate count-fuel mass)
       rest
       (take-while (complement neg?))
       (reduce +)))

(defn part-2 []
  (transduce (map total-fuel) + data))
