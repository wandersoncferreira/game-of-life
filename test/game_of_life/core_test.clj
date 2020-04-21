(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest a-single-cell-should-have-zero-neighbors
  (let [board (make-board)
        cell (make-cell)]
    (is (= (:neighbors (add-cell board cell)) 0))))
