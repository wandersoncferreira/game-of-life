(ns game-of-life.core)

(defn make-board
  ([]
   {:cells     []})
  ([cells]
   {:cells cells}))

(defn make-cell [x y]
  {:x x
   :y y
   :alive? true})

(defn- abs [v]
  (if (pos? v) v (* -1 v)))

(defn- neighbors? [p1 p2]
  (let [[x1 y1] p1
        [x2 y2] p2
        diff-x (abs (- x1 x2))
        diff-y (abs (- y1 y2))]
    (and (<= diff-x 1)
         (<= diff-y 1))))

(defn neighbors [board cell]
  (let [cells-alive (filter (fn [cell] (not (:alive cell))) (:cells board))]
    (for [{:keys [x y] :as c} cells-alive
          :when               (and
                               (not= c cell)
                               (neighbors? [x y] [(:x cell) (:y cell)]))]
      c)))

(defn add-cell [board cell]
  (update board :cells conj cell))

(defn make-game [board]
  {:board board})

(defn next-iteration [{:keys [board] :as game}]
  (let [cells (get-in game [:board :cells])]
    (make-game
     (make-board
      (for [c cells]
        (if (= (count (neighbors board c)) 2)
          (assoc c :alive? true)
          (assoc c :alive? false)))))))

(defn is-alive? [board cell]
  (let [{:keys [x y]} cell]
    (:alive?
     (first (filter (fn [c]
                      (and (= (:x c) x)
                           (= (:y c) y)))
                    (:cells board))))))
