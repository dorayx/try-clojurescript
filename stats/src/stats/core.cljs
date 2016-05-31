(ns stats.core
  (:require ))

(enable-console-print!)

(println "This text is printed from src/stats/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn mean
  "Calculate the average of the given numbers."
  [numbers]
  (/ (reduce + numbers) (count numbers)))

(defn median
  "Calculate the mediam of the given numbers."
  [numbers]
  (let [n (count numbers)
        is-even (even? n)
        midth (int (/ n 2))
        drop-count (if is-even midth (- midth 1))
        remains (drop drop-count (sort numbers))]
    (if is-even
      (first remains)
      (/ (+ (first remains) (second remains)) 2))))

(defn get-sums
  [acc cur]
  (let [sum-x (first acc)
        sum-x2 (second acc)]
    (vector (+ sum-x cur) (+ sum-x2 (* cur cur)))))

(defn stdev
  "Calculate the standard deviation of the given numbers."
  [numbers]
  (let [n (count numbers)
        [sum-x sum-x2] (reduce get-sums [0 0] numbers)
        numerator (- sum-x2 (/ (* sum-x sum-x) n))
        denominator (- n 1)]
    (.sqrt js/Math (/ numerator denominator))))