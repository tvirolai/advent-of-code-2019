(ns advent-of-code-2019.day05
  (:require [clojure.string :as s]))

(defn load-data []
  (let [raw (slurp "./data/day05.txt")]
    (->> (s/split raw #",")
         (mapv read-string))))

(defn parse-instructions
  ([data] (parse-instructions data []))
  ([data res]
   (if (= 99 (first data))
     (into res data)
     (let [dir-length (if (#{3 4} (first data)) 2 4)
           _ (println dir-length)]
       (recur (subvec data (inc dir-length))
              (conj res (subvec data 0 dir-length)))))))

(defn process [])
