(ns practicalli.random-clojure-function
  (:gen-class))


(def standard-library-functions
  "Fully qualified function names from clojure.core"
  (vals (ns-publics 'clojure.core)))

(defn random-function
  [function-list]
  (let [function-details (meta (rand-nth function-list))]
    (str (function-details :name)
         "\n  " (function-details :arglists  )
         "\n  " (function-details :doc) )))


(defn -main
  "Return a function name from the Clojure Standard library"
  [& args]
  (println (random-function standard-library-functions)))


;; REPL experiments
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment

  (-main )


  (ns-publics 'clojure.core)

  (vals (ns-publics 'clojure.core))

  (rand-nth (vals (ns-publics 'clojure.core)))

  (all-ns)

  )
