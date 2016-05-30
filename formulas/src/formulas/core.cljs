(ns formulas.core
	(:require [clojure.browser.repl :as repl]))

(defonce conn
	(repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

;; etude 1-2

(defn distance
	[accel time]
	(/ (* accel time time) 2.0))

(defn kinetic-energy
	[m v]
	(/ (* m v v) 2.0))

(defn centripetal
	[v r]
	(/ (* v v) r))

;; etude 1-3

(def G 6.6784e-11)

(defn gravitational-force
  "Calculate the gravitational force between two masses m1 and m2
   with centers of gravity at a distance r"
	[m1 m2 r]
	(/ (* G m1 m2) (* r r)))

;; etude 1-4

(defn monthly-payment
  "Calculate the monthly payment on an amount of loan p
   with an annual percentage rate apr and a number of years"
	[p apr years]
	(let [r (/ (/ apr 100) 12.0)
		    n (* years 12)
		    f (.pow js/Math (+ 1 r) n)]
		(* p (/ (* r f) (- f 1)))))

;; etude 1-5

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