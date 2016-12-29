(ns nature-code.chap1.gaussian
  (:require [quil.core :as q]))

(def generator (new java.util.Random))

(defn setup []
  (q/background 0)
  (q/no-stroke)
  (q/fill 255, 10))

(defn draw []
  (let [num (float (. generator nextGaussian))
        sd 60
        mean 320
        x (+ mean (* sd num))]
    (q/ellipse x, 180, 16, 16)))

(q/defsketch example
  :setup setup
  :draw draw
  :size [640 360])
