(ns advent-of-code-2019.day02
  (:require [clojure.string :as s]))

(defn load-data [noun verb]
  (let [raw (slurp "./data/day02.txt")]
    (assoc (->> (s/split raw #",")
                (mapv read-string))
           1 noun
           2 verb)))

(defn process
  ([input] (process input 0))
  ([input index]
   (let [[op1 op2 op3 op4 & _] (subvec input index (+ 4 index))
         operation (if (= op1 1) + *)]
     (if (= 99 op1)
       (first input)
       (recur (assoc input op4 (operation (nth input op2)
                                          (nth input op3)))
              (+ index 4))))))

(defn part-1 []
  (process (load-data 12 2)))

(defn part-2 []
  (loop [noun 12
         verb 2]
    (let [input (load-data noun verb)
          target 19690720]
      (cond
        (= target (process input)) (+ verb (* 100 noun))
        (and (> target (process input))
             (< target (process (load-data (+ noun 2) verb)))) (recur noun (+ 2 verb))
        :else (recur (+ 2 noun) verb)))))
