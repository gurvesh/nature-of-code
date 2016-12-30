(ns nature-code.chap1.gaussian-color-palette
  (:require [quil.core :as q]))

(def r-generator (new java.util.Random))

(defn setup []
  (q/background 0)
  (q/no-stroke))

(defn draw []
  (let [num (float (. r-generator nextGaussian))
        sd 60
        mean 0
        r (+ mean (* sd num))
        theta (q/random (* 2 (Math/PI)))
        x (+ 320 (* r (Math/sin theta)))
        y (+ 320 (* r (Math/cos theta)))
        theta1 (rem (+ theta (* 2/3 (Math/PI)))
                    (* 2 (Math/PI)))
        theta2 (rem (+ theta (* 4/3 (Math/PI)))
                    (* 2 (Math/PI)))
        r (int (* 255 (/ theta (* 2 (Math/PI)))))
        g (int (* 255 (/ theta1 (* 2 (Math/PI)))))
        b (int (* 255 (/ theta2 (* 2 (Math/PI)))))]
    (q/fill r, g, b, 10)
    (q/ellipse x, y, 16, 16)))

(q/defsketch example
  :setup setup
  :draw draw
  :size [640 640])
