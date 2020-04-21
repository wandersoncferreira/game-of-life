(ns game-of-life.core)

(defn make-board
  ([]
   {:cells     []})
  ([cells]
   {:cells cells}))

(defn make-cell
  ([x y]
   (make-cell x y true))
  ([x y alive?]
   {:x      x
    :y      y
    :alive? alive?}))

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

(defn- create-neighborhood [{:keys [x y]}]
  (for [i (range (- x 1) (+ x 2))
        j (range (- y 1) (+ y 2))]
    (make-cell i j false)))

(defn should-reborn? [board cell]
  (let [n (neighbors board cell)]
    (and (false? (:alive? cell))
         (= 3 (count n)))))

(defn next-iteration [{:keys [board] :as game}]
  (let [next (loop [cells            (get-in game [:board :cells])
                    next-iter-cells  []
                    potencial-reborn []]
               (if (empty? cells)
                 {:next-iter-cells  next-iter-cells
                  :potencial-reborn potencial-reborn}
                 (let [cell (first cells)
                       n    (count (neighbors board cell))]
                   (if (or (= n 2) (= n 3))
                     (recur (rest cells)
                            (conj next-iter-cells (assoc cell :alive? true))
                            (concat potencial-reborn (create-neighborhood cell)))
                     (recur (rest cells)
                            (conj next-iter-cells (assoc cell :alive? false))
                            (concat potencial-reborn (create-neighborhood cell)))))))
        reborn (->> next
                    :potencial-reborn
                    flatten
                    (filter (fn [r] (should-reborn? board r)))
                    (map #(assoc % :alive? true)))]
    (make-game
     (make-board (concat (:next-iter-cells next) reborn)))))

(defn is-alive? [{:keys [board]} cell]
  (let [{:keys [x y]} cell]
    (:alive?
     (first (filter (fn [c]
                      (and (= (:x c) x)
                           (= (:y c) y)))
                    (:cells board))))))

(defn print-game-of-life [game size]
  (letfn [(print-alive [[x y]]
            (if (is-alive? game (make-cell x y))
              (print "X")
              (print " ")))
          (print-game! [i]
            (run! print-alive (map #(vector i %) (range (* -1 size) size)))
            (println " "))]
    (run! print-game! (range (* -1 size) size))))
