(ns daylight-enfocus.core
  (:require [enfocus.core :as ef]
            [enfocus.events :as ev]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn radians
  "Convert degrees to radians"
  [degrees]
  (* (/ (.-PI js/Math) 180) degrees))

(defn daylight
  [latitude day]
  (let [lat (radians latitude)
        p (.asin js/Math (* 0.39795 (.cos js/Math (+ 0.2163108 (* 2 (.atan js/Math (* 0.9671396 (.tan js/Math (* 0.00860 (- day 186))))))))))
        d (- 24 (* 7.63944 (.acos js/Math (/ (+ (.sin js/Math 0.01454) (* (.sin js/Math lat) (.sin js/Math p))) (* (.cos js/Math lat) (.cos js/Math p))))))]
    (* d 60)))

(defn floatize-input
  "Parse the value of the input field into a float number"
  [el-input]
  (js/parseFloat el-input))

(defn calculate-daylight
  "Calculate the daylight"
  [evt]
  (let [prop-getter (ef/get-prop :value)
        lat (floatize-input (ef/from "#latitude" prop-getter))
        year (floatize-input (ef/from "#julian" prop-getter))
        result (daylight lat year)
        result-setter (ef/content (.toString result))]
    (ef/at "#result" result-setter)))

(let [evt-listener (ev/listen :click calculate-daylight)]
  (ef/at "#calculate" evt-listener))