(ns nature-code.chap1.random-distribution
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def random-count 20)

(defn setup []
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  {:random-counts (vec (repeat random-count 0))})

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
   (let [w (/ (q/width)
              random-count)]
     (map #(q/rect (* % w), (- (q/height) %2), (dec w), %2)
          (range random-count)
          (:random-counts state)))))

(q/defsketch example
  :setup setup
  :draw draw
  :update update-state
  :size [640 360]
  :middleware [m/fun-mode])
