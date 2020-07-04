(ns practicalli.random-clojure-function
  (:gen-class))


;; data

(def standard-library-functions
  "Fully qualified function names from clojure.core"
  (vals (ns-publics 'clojure.core)))

(def all-public-functions
  "Fully qualified function names from available"
  (mapcat #(vals (ns-publics %)) (all-ns)))


;; helper functions

(defn function-list
  [namespace]
  (vals (ns-publics namespace)))

(defn selective-namespace-functions
  [namespace-sequence]
  (mapcat #(function-list (symbol %)) namespace-sequence))


;; logic

(defn random-function
  [function-list]
  (let [function-details (meta (rand-nth function-list))]
    (str (function-details :ns) "/" (function-details :name)
         "\n  " (function-details :arglists)
         "\n  " (function-details :doc))))



(defn -main
  "Return a random function and its details
  from the available namespaces"
  [& args]
  (if (seq args)
    (println (random-function (selective-namespace-functions args)))
    (println (random-function all-public-functions))))


;; REPL experiments
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment

  (-main )


  (ns-publics 'clojure.core)

  (vals (ns-publics 'clojure.core))

  (rand-nth (vals (ns-publics 'clojure.core)))

  (all-ns)

  )
