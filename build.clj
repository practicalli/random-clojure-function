(ns build
  (:require
   [clojure.tools.build.api :as build-api]
   [clojure.pprint :as pprint]))

;; ---------------------------------------------------------
;; Build configuration

(def project-config
  "Project configuration to support all tasks"
  (let [library-name 'practicalli/random-clojure-function
        version (format "1.0.%s" (build-api/git-count-revs nil))]
    {:library-name    library-name
     :main-namespace  library-name
     :project-version version
     :class-directory "target/classes"
     :project-basis   (build-api/create-basis)
     :jar-file        (format "target/%s-%s.jar" (name library-name) version)
     :uberjar-file    (format "target/%s-%s-standalone.jar" (name library-name) version)}))

;; End of Build configuration
;; ---------------------------------------------------------

;; ---------------------------------------------------------
;; Build tasks

(defn clean
  "Remove a directory
  - `:path '\"directory-name\"'` for a specific directory
  - `nil` (or no command line arguments) to delete `target` directory
  `target` is the default directory for build artefacts"
  [directory]
  (build-api/delete {:path (or (:path directory) "target")}))

(defn jar
  "Create a build of the project, cleaning existing build assets first"
  [_]
  (let [{:keys [class-directory jar-file library-name project-basis project-version]} project-config]
    (clean nil)
    (pprint/pprint project-config)
    (build-api/write-pom {:class-dir class-directory
                          :lib       library-name
                          :version   project-version
                          :basis     project-basis
                          :src-dirs  ["src"]})
    (build-api/copy-dir {:src-dirs   ["src" "resources"]
                         :target-dir class-directory})
    (build-api/jar {:class-dir class-directory
                    :jar-file  jar-file})))

(defn uberjar
  "Create an archive containing Clojure and the build of the project"
  [_]
  (let [{:keys [class-directory main-namespace project-basis uberjar-file]} project-config]
    (clean nil)
    (build-api/copy-dir {:src-dirs   ["src" "resources"]
                         :target-dir class-directory})

    (build-api/compile-clj {:basis     project-basis
                            :src-dirs  ["src"]
                            :class-dir class-directory})

    (build-api/uber {:class-dir class-directory
                     :uber-file uberjar-file
                     :basis     project-basis
                     :main      main-namespace})))

;; End of Build tasks
;; ---------------------------------------------------------
