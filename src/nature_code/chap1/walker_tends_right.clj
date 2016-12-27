(ns nature-code.chap1.walker-tends-right
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/background 255)
  (q/stroke 0)
  {:x (* 1/2 (q/width))
   :y (* 1/2 (q/height))})

(defn step [state]
  (let [r (q/random 1)]
    (cond (< r 0.4) (update state :x inc)
          (< r 0.6) (update state :x dec)
          (< r 0.8) (update state :y inc)
          :else (update state :y dec))))

(defn update-state [state]
  (step state))

(defn draw [state]
  (q/point (:x state) (:y state)))

(q/defsketch example
  :setup setup
  :draw draw
  :update update-state
  :size [640 360]
  :middleware [m/fun-mode])
