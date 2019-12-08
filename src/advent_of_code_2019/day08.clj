(ns advent-of-code-2019.day08
  (:require [clojure.string :as s]))

(def data
  (map #(Character/digit % 10)
       (s/trim (slurp "./data/day08.txt"))))

(defn to-layers [data]
  (partition (* 25 6) data))

(defn part-1 []
  (->> data
       to-layers
       (map (fn [layer]
              {:layer layer
               :zeros (-> layer frequencies (get 0))}))
       (sort-by #(min (:zeros %)))
       first
       :layer
       frequencies
       ((juxt #(get % 1)
              #(get % 2)))
       (reduce *)))

(defn- apply-layer [layer grid]
  (vec (map-indexed (fn [idx itm]
                      (let [grid-val (get grid idx)]
                        (if (= 2 grid-val)
                          itm
                          grid-val)))
                    layer)))

(defn part-2 []
  (loop [layers (to-layers data)
         grid (vec (take 150 (repeat 2)))]
    (if (empty? layers)
      (partition 25 grid)
      (recur (rest layers)
             (apply-layer (first layers) grid)))))
