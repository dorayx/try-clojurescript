(ns stats.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [clojure.string :as str]))

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

(defn read-numbers
  "Read input numbers."
  [el-input]
  (let [str-numbers (str/split (dommy/value el-input) #"[\s,]+")]
    (map (fn [x] (js/parseFloat x)) str-numbers)))

(defn calculate
  "Calculate the mean, median and stdev."
  [evt]
  (let [numbers (read-numbers (.-target evt))
        el-mean (sel1 "#mean")
        el-median (sel1 "#median")
        el-stdev (sel1 "#stdev")
        val-mean (mean numbers)
        val-median (median numbers)
        val-stdev (stdev numbers)]
    (dommy/set-text! el-mean val-mean)
    (dommy/set-text! el-median val-median)
    (dommy/set-text! el-stdev val-stdev)))

(let [el-input (sel1 "#numbers")]
  (dommy/listen! el-input :change calculate))