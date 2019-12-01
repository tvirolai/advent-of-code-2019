(ns advent-of-code-2019.day01
  (:require [clojure.string :as s]))

(def data
  (let [lines (-> "./data/day01.txt" slurp (s/split #"\n"))]
    (map #(Integer/parseInt %) lines)))

(defn count-fuel [mass]
  (- (Math/floor (/ mass 3)) 2))

(defn part-1 []
  (reduce + (map count-fuel data)))

(defn total-fuel [mass]
  (->> (iterate count-fuel mass)
       rest
       (take-while (complement neg?))
       (reduce +)
       int))

(defn part-2 []
  (transduce (map total-fuel) + data))
