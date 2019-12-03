(ns advent-of-code-2019.day03
  (:require [clojure.string :as s]
            [clojure.set :refer [intersection]]))

(defrecord Instruction [direction amount])

(defrecord Coordinate [x y])

(defn parse-item [item]
  (->Instruction (keyword (str (first item)))
                 (read-string (re-find #"\d+" item))))

(defn load-data [filename]
  (let [lines (-> filename slurp s/trim (s/split #"\n"))
        entries (map #(s/split % #",") lines)]
    (for [line entries]
      (mapv parse-item line))))

(defn next-points [coord instruction]
  (loop [{:keys [x y] :as c} coord
         {:keys [direction amount]} instruction
         res []]
    (if (zero? amount)
      (conj res c)
      (let [next-instr (->Instruction direction (if (pos? amount)
                                                  (dec amount)
                                                  (inc amount)))]
        (condp = direction
          :U (recur (->Coordinate x (inc y)) next-instr (conj res c))
          :D (recur (->Coordinate x (dec y)) next-instr (conj res c))
          :L (recur (->Coordinate (dec x) y) next-instr (conj res c))
          :R (recur (->Coordinate (inc x) y) next-instr (conj res c)))))))

(defn calculate-coords [coord instructions result]
  (if (empty? instructions)
    (dedupe result)
    (let [points (next-points coord (first instructions))]
      (recur (last points)
             (rest instructions)
             (into result points)))))

(defn- manhattan-distance [{:keys [x y]}]
  (+ (Math/abs x) (Math/abs y)))

(defn part-1 []
  (let [[line1 line2] (load-data "./data/day03.txt")
        coords1 (->> (calculate-coords (->Coordinate 0 0) line1 [])
                     set)
        coords2 (->> (calculate-coords (->Coordinate 0 0) line2 [])
                     set)]
    (->> (intersection coords1 coords2)
         (map manhattan-distance)
         sort
         second)))

(defn part-2 []
  (let [[line1 line2] (load-data "./data/day03.txt")
        coords1 (calculate-coords (->Coordinate 0 0) line1 [])
        coords2 (calculate-coords (->Coordinate 0 0) line2 [])]
    (loop [intersections (intersection (set coords1)
                                       (set coords2))
           res '()]
      (if (empty? intersections)
        (second (sort res))
        (recur (rest intersections)
               (conj res (+ (.indexOf coords1 (first intersections))
                            (.indexOf coords2 (first intersections)))))))))
