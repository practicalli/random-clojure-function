(ns practicalli.random-clojure-function
  (:gen-class))


(def standard-library-functions
  "Fully qualified function names from clojure.core"
  (vals (ns-publics 'clojure.core)))



(defn -main
  "Return a function name from the Clojure Standard library"
  [& args]
  (let [function-details (meta (rand-nth standard-library-functions))]
    (str (function-details :name) "\n  " (function-details :doc))))


;; REPL experiments
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment

  (-main )


  (ns-publics 'clojure.core)

  (vals (ns-publics 'clojure.core))

  (rand-nth (vals (ns-publics 'clojure.core)))

  (all-ns)

  )
