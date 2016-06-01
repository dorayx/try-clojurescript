(ns make-teeth.core
  (:require ))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn one-tooth
  "Generate one tooth."
  [present probability]
  (if (= present "F")
    []
    (let [base-depth (if (< (rand) probability) 2 3)
          placeholders [0 0 0 0 0 0]]
      (mapv (fn [] (+ base-depth (- 1 (rand-int 3)))) placeholders))))

(defn generate-list
  "Take a list of teeth, probability and the current vector of vectors.
   Add pockets for each tooth."
  [teeth-present probability result]
  (if (empty? teeth-present)
    result
    (recur (rest teeth-present) probability
      (conj result (one-tooth (first teeth-present) probability)))))

(defn generate-pockets
  "Take a list of teeth present and probability of a good tooth,
   and create a list of pocket depths."
  [teeth-present probability]
  (generate-list teeth-present probability []))

