(ns game-of-life.core)

(defn make-board []
  {:neighbors 0
   :cells []})

(defn make-cell [x y]
  {:x x :y y})

(defn- abs [v]
  (if (pos? v) v (* -1 v)))

(defn- neighbors? [p1 p2]
  (let [[x1 y1] p1
        [x2 y2] p2]
    (or (and (= x1 x2) (= 1 (abs (- y1 y2))))
        (and (= y1 y2) (= 1 (abs (- x1 x2)))))))

(defn neighbors [board cell]
  (for [{:keys [x y] :as c} (:cells board)
        :when (and
               (not= c cell)
               (neighbors? [x y] [(:x cell) (:y cell)]))]
    c))

(defn add-cell [board cell]
  (update board :cells conj cell))