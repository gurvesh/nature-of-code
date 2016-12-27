(ns nature-code.chap1.random-distribution
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  {:random-counts (vec (repeat 20 0))})

(defn update-state [state]
  (let [index (->> state
                   :random-counts
                   count
                   q/random
                   int)]
    (update-in state [:random-counts]
               #(update % index inc))))

(defn draw [state]
  (doall
   (let [random-count (count (:random-counts state))
         w (/ (q/width)
              random-count)]
     (for [i (range random-count)]
       (let [x (nth (:random-counts state) i)]
         (q/rect (* i w), (- (q/height) x), (dec w), x))))))

(q/defsketch example
  :setup setup
  :draw draw
  :update update-state
  :size [640 360]
  :middleware [m/fun-mode])
