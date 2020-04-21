(ns game-of-life.core)

(defn make-board []
  {:neighbors 0
   :cells []})

(defn make-cell [x y]
  {:x x :y y})

(defn neighbors [board cell]
  (for [{:keys [x y] :as c} (:cells board)
        :when (and
               (not= c cell)
               (or (= (:x cell) x)
                   (= (:y cell) y)))]
    c))

(defn add-cell [board cell]
  (update board :cells conj cell))
