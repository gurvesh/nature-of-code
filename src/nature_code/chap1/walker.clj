(ns nature-code.chap1.walker
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 255)
  (q/stroke 0)
  {:x (* 1/2 (q/width))
   :y (* 1/2 (q/height))})

(defn step [x]
  (+ x (q/random -1 1)))

(defn update-state [state]
  (reduce #(update-in % [%2] step)
          state
          [:x :y]))

(defn draw [state]
  (q/point (:x state) (:y state)))

(q/defsketch example
  :setup setup
  :draw draw
  :update update-state
  :size [640 360]
  :middleware [m/fun-mode])
