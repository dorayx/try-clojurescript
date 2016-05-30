(ns daylight-js.core
	(:require [clojure.browser.repl :as repl]))

(defonce conn
	(repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

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

(defn $
  "Query the HTML element by the given ID"
  [id]
  (.getElementById js/document id))

(defn floatize-input
	"Parse the value of the input field into a float number"
	[el-input]
	(.parseFloat js/window (.-value el-input)))

(defn set-el-value
  "Set the value of the given element"
  [el value]
  (set! (.-innerHTML el) value))

(defn calculate-daylight
  "Calculate the daylight"
  [evt]
  (let [el-lat ($ "latitude")
         el-year ($ "julian")
         el-result ($ "result")
         lat (floatize-input el-lat)
         year (floatize-input el-year)
         result (daylight lat year)]
    (set-el-value el-result result)))

(let [el-calc ($ "calculate")]
  (.addEventListener el-calc "click" calculate-daylight))
