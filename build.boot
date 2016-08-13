;; Dependency Helper Fn's ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn read-file   [file] (read-string (slurp file)))
(defn get-deps    []     (read-file "./dependencies.edn"))
;(defn get-devdeps []     (read-file "./dev_dependencies.edn"))

;; Boot Env Settings ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(set-env!
 :dependencies   (get-deps)
 :resource-paths #{"src" "resources"})

;; Require Boot Task Namespaces ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(require
 '[adzerk.bootlaces :refer :all]
 '[hoplon.boot-hoplon :refer :all]
 '[boot-semver.core :refer :all]
 '[tolitius.boot-check :as check]
 '[funcool.boot-codeina :refer :all]
 '[adzerk.boot-cljs :refer :all]
 '[pandeiro.boot-http :refer :all]
 '[adzerk.boot-reload :refer [reload]])

;; Boot Task Options ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(task-options!
 pom    {:project 'degree9/blaze
         :version (get-version)
         :description "A (No Backend) Blog"
         :url "https://github.com/degree9/blaze"
         :scm {:url "https://github.com/degree9/blaze"}}
 cljs   {:source-map       true
         :compiler-options {:pseudo-names true
                            :pretty-print true
                            :language-in :ecmascript5
                            :parallel-build true}}
 apidoc {:version (get-version)
         :reader :clojurescript
         :title "Blaze"
         :sources #{"target"}
         :description "A (No Backend) Blog"})

;; Boot Tasks ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftask tests
  "Run code tests."
  []
  (comp
    (check/with-kibit)
    (check/with-yagni)))

(deftask docs
  "Generate Documentation."
  []
  (comp
    (hoplon)
    (sift :to-asset #{#"[.]*.hl$"} :invert true)
    (target :dir #{"target"})
    (apidoc)))

(deftask deploy
  "Build project for deployment to clojars."
  []
  (comp
    (version :minor 'inc :patch 'zero)
    (hoplon  :manifest true)
    (build-jar)
    (push-release)))

(deftask develop
  "Build project for development."
  []
  (comp
    (watch)
    (version :no-update true
             :minor 'inc
             :patch 'zero
             :pre-release 'snapshot)
    (hoplon :manifest true)
    (build-jar)))

(deftask demo
  "Run a demo of the project."
  []
  (comp
    (watch)
    (hoplon)
    (reload)
    (cljs :optimizations :none)
    (serve :port 8080)))
