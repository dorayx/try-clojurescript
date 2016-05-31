(ns daylight-by-date.core
  (:require [clojure.string :as str]
            [dommy.core :as dommy :refer-macros [sel1]]))

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

(defn leap-year?
  "Return true if given year is a leap year; false otherwise."
  [year]
  (let [rem4 (rem year 4)
        rem100 (rem year 100)
        rem400 (rem year 400)]
    (or (and (= 0 rem4) (not= 0 rem100)) (= 0 rem400))))

(defn days-per-month
  "Return the total days of the given month."
  [month year]
  (case month
    2 (if (leap-year? year) 29 28)
    (1 3 5 7 8 10 12) 31
    30))

(defn validate-date?
  "Return true if the input date is validated; false otherwise."
  [day month year]
  (let [valid-year (integer? year)
        valid-month (and valid-year (integer? month) (<= month 12) (>= month 1))]
    (and valid-month (integer? day) (>= day 1) (<= day (days-per-month month year)))))

(defn ordinal-day
  "Calculate the ordinal (Julian) day with the given Gregorian day, month and year."
  [day month year]
  (let [valid-date (validate-date? day month year)
        prev-months (range 1 month)
        prev-days (map (fn [x] (days-per-month x year)) prev-months)
        total-days (+ (reduce + prev-days) day)]
    (if valid-date total-days 0)))

(defn gregorian-to-julian
  "Convert Gregorian date to Julian"
  [gregorian]
  (let [str-parts (str/split gregorian #"-")
        [year month day] (map (fn [x] (js/parseInt x 10)) str-parts)]
    (ordinal-day day month year)))

(defn calculate-daylight
  []
  (let [el-lat (sel1 "#latitude")
        el-date (sel1 "#gregorian")
        el-result (sel1 "#result")
        latitude (js/parseFloat (dommy/value el-lat))
        day (gregorian-to-julian (dommy/value el-date))
        result (daylight latitude day)]
    (dommy/set-text! el-result result)))

(let [el-calc (sel1 "#calculate")]
  (dommy/listen! el-calc "click" calculate-daylight))