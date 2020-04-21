(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest a-single-cell-should-have-zero-neighbors
  (let [board (make-board)
        cell (make-cell 0 0)]
    (is (= (count (neighbors board (add-cell board cell))) 0))))

(deftest two-cells-next-to-each-other-should-be-neighbors
  (let [board (reduce
               (fn [board [x y]]
                 (add-cell board (make-cell x y)))
               (make-board)
               [[0 0] [0 1]])]
    (is (= (neighbors board (make-cell 0 0)) [(make-cell 0 1)]))
    (is (= (neighbors board (make-cell 0 1)) [(make-cell 0 0)]))))

(deftest a-two-single-cells-far-from-each-other-should-have-zero-neighbors
  (let [board (reduce
               (fn [board [x y]]
                 (add-cell board (make-cell x y)))
               (make-board)
               [[0 0] [0 100]])]
    (is (= (count (neighbors board (make-cell 0 0))) 0))
    (is (= (count (neighbors board (make-cell 0 100))) 0))))

(deftest middle-cell-of-3x3-block-should-have-eight-neighbors
  (let [board (reduce
               (fn [board [x y]]
                 (add-cell board (make-cell x y)))
               (make-board)
               [[0 0] [0 1] [0 2]
                [1 0] [1 1] [1 2]
                [2 0] [2 1] [2 2]])]
    (is (= 8 (count (neighbors board (make-cell 1 1)))))))

(deftest a-single-cell-should-die-in-next-iteration
  (let [board (make-board)
        board (add-cell board (make-cell 0 0))
        game (make-game board)
        new-game (next-iteration game)]
    (is (false? (is-alive? (:board new-game) (make-cell 0 0))))))

(deftest a-cell-with-two-neighbors-should-live-in-next-iteration
  (let [board (reduce
               (fn [board [x y]]
                 (add-cell board (make-cell x y)))
               (make-board)
               [[0 1] [1 1] [1 0]])
        game (make-game board)
        new-game (next-iteration game)]
    (is (true? (is-alive? (:board new-game) (make-cell 1 1))))))

(deftest a-cell-with-three-neighbors-should-live-in-next-iteration
  (let [board (reduce
               (fn [board [x y]]
                 (add-cell board (make-cell x y)))
               (make-board)
               [[0 1] [1 1] [1 0] [0 0]])
        game (make-game board)
        new-game (next-iteration game)]
    (is (true? (is-alive? (:board new-game) (make-cell 1 1))))))
