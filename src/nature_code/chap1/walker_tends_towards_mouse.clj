(ns nature-code.chap1.walker-tends-towards-mouse
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def pull-prob 0.10)

(defn setup []
  (q/background 255)
  (q/stroke 0)
  {:x (* 1/2 (q/width))
   :y (* 1/2 (q/height))
   :pull-to-x (q/width)
   :pull-to-y (q/height)})

(defn move-to-angular [theta, {:keys [x, y] :as state}]
  (let [new-x (+ x (Math/cos theta))
        new-y (+ y (Math/sin theta))]
    (-> state
        (assoc :x new-x)
        (assoc :y new-y))))

(defn move-towards-pull [{:keys [x, y, pull-to-x, pull-to-y]
                          :as state}]
  (let [theta (Math/atan (/ (- pull-to-y, y)
                            (- pull-to-x, x)))
        theta (if (> x pull-to-x)
                (+ (Math/PI) theta)
                theta)]
    (move-to-angular theta state)))

(defn move-randomly [state]
  (let [theta (q/random (* 2 (Math/PI)))]
    (move-to-angular theta state)))

(defn step [state]
  (let [r (q/random 1)]
    (if (< r pull-prob)
      (move-towards-pull state)
      (move-randomly state))))

(defn update-state [state]
  (step state))

(defn mouse-moved [state event]
  (-> state
      (assoc :pull-to-x (:x event))
      (assoc :pull-to-y (:y event))))

(defn draw [state]
  (q/point (:x state) (:y state)))

(q/defsketch example
  :setup setup
  :draw draw
  :update update-state
  :size [640 360]
  :mouse-moved mouse-moved
  :middleware [m/fun-mode])
