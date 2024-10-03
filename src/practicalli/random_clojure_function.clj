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
;;

(comment

  ;; A hash-map of functions in clojure.core
  (ns-publics 'clojure.core)

  ;; a sequence of function vars from clojure.core
  (vals (ns-publics 'clojure.core))

  ;; random var from the sequence
  (rand-nth (vals (ns-publics 'clojure.core)))

  ;; metadata from a function var
  (meta #'map)
  ;; => {:added "1.0",
  ;;     :ns #namespace[clojure.core],
  ;;     :name map,
  ;;     :file "clojure/core.clj",
  ;;     :static true,
  ;;     :column 1,
  ;;     :line 2727,
  ;;     :arglists ([f] [f coll] [f c1 c2] [f c1 c2 c3] [f c1 c2 c3 & colls]),
  ;;     :doc
  ;;     "Returns a lazy sequence consisting of the result of applying f to\n  the set of first items of each coll, followed by applying f to the\n  set of second items in each coll, until any one of the colls is\n  exhausted.  Any remaining items in other colls are ignored. Function\n  f should accept number-of-colls arguments. Returns a transducer when\n  no collection is provided."}


  (meta (rand-nth (vals (ns-publics 'clojure.core))))

  ;; Get all namespaces from the current classpath
  (all-ns)

  (require '[clojure.inspector :as inspector])
  (inspector/inspect-tree (all-ns))



  (mapcat function-list ['clojure.core])

  (mapcat #(function-list (symbol %)) ['clojure.core])
  (mapcat #(function-list (symbol %)) ["clojure.core"])

  (mapcat function-list ['clojure.core 'clojure.string])


  (ns-publics (var map))

  (symbol 'clojure.core)

  (function-list 'clojure.core)

  (-main "clojure.string")



  ;; call main without arguments uses Clojure standard library
  ;; plus any other namespaces in the REPL
  (-main)

  ;; calling with a specific namespace will return functions only from that namespace
  ;; strings
  (-main "clojure.string")

  )
