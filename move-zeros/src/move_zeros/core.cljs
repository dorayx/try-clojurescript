(ns move-zeros.core
	(:require [clojure.browser.repl :as repl]))

(defonce conn
	(repl/connect "http://localhost:9000/repl"))

(enable-console-print!)

(defn get-non-zeros
  "Filter the non-zero numbers"
  [integers]
  (filter (fn [x] (not= x 0)) integers))

(defn move-zeros
  "Move all the zeros to the end of a list of numbers"
  [integers]
  (let [non-zeros (get-non-zeros integers)
        zeros-length (- (count integers) (count non-zeros))
        zeros (repeat zeros-length 0)]
    (concat non-zeros zeros)))