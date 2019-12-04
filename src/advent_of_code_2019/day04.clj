(ns advent-of-code-2019.day04)

(defn- to-digits [n]
  (map #(Character/digit % 10) (str n)))

(defn adjacent-number-counts [n]
  (->> n
       to-digits
       (partition-by identity)
       (sort-by count)
       (map count)
       set))

(defn not-decreasing? [n]
  (apply <= (to-digits n)))

(defn part-1 []
  (->> (range 256310 732736)
       (filter (every-pred #(< 1 (apply max (adjacent-number-counts %))) not-decreasing?))
       count))

(defn part-2 []
  (->> (range 256310 732736)
       (filter (every-pred #((adjacent-number-counts %) 2) not-decreasing?))
       count))
