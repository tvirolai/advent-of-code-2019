(ns advent-of-code-2019.day06
  (:require [clojure.string :as s]
            [clojure.set :as cset]))

(defrecord Instruction [target satellite])

(def data
  (let [lines (-> (slurp "./data/day06_test2.txt")
                  (s/split #"\n"))]
    (for [l lines
          :let [[t s] (s/split l #"\)")]]
      (->Instruction t s))))

(defn path-length [data to from]
  (loop [current from
         path-length 0]
    (cond
      (nil? current) 0
      (= current to) path-length
      :else (recur (->> data
                        (filter #(= current (:satellite %)))
                        first
                        :target)
                   (inc path-length)))))

(defn part-1 []
  (let [satellites (map :satellite data)
        targets (map :target data)
        root (first (cset/difference (set targets)
                                     (set satellites)))]
    (->> satellites
         (map (partial path-length data root))
         (reduce +))))

(defn part-2 []
  (let [targets (map :target data)
        path-lengths (for [t targets
                           :let [from-you (path-length data t "YOU")
                                 from-san (path-length data t "SAN")]
                           :when (and (pos? from-you)
                                      (pos? from-san))]
                       (+ (dec (path-length data t "YOU"))
                          (dec (path-length data t "SAN"))))]
    (->> path-lengths
         sort
         first)))
