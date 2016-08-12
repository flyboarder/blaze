(ns blaze.bidi
  (:require [hoplon.bidi :as bidi]))

(def ^:dynamic *prefix* "_blaze")

(defn routes
  [& [prefix]]
  [(or prefix *prefix*)
    {#{"" "/"} :index
       "tags" {#{"" "/"} :tags}
       "admin" {#{"" "/"} :admin}
       "pages" {#{"" "/"} :pages}
       "posts" {#{"" "/"} :posts}
       "categories" {#{"" "/"} :categories}
       true :not-blaze}])

(defn route [& [prefix]]
  (bidi/route (routes prefix))

(defn route? [handler & [prefix]]
  (bidi/route? (routes prefix) handler))
