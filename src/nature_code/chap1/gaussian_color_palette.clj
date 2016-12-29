(ns nature-code.chap1.gaussian-color-palette
  (:require [quil.core :as q]))

(def r-generator (new java.util.Random))

(defn setup []
  (q/background 255)
  (q/no-stroke))

(defn get-color [theta]
  (cond ))

(defn draw []
  (let [num (float (. r-generator nextGaussian))
        sd 60
        mean 0
        r (+ mean (* sd num))
        theta (q/random (* 2 (Math/PI)))
        x (+ 320 (* r (Math/sin theta)))
        y (+ 320 (* r (Math/cos theta)))
        color (get-color theta)]
    (q/fill 255, 0, 0, 10)
    (q/ellipse x, y, 16, 16)))

(q/defsketch example
  :setup setup
  :draw draw
  :size [640 640])
