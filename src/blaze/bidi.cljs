(ns blaze.bidi
  (:require [hoplon.bidi :as bidi]))

(def ^:dynamic *prefix* "_blaze")

(def ^:dynamic routes
  [*prefix*
    {#{"" "/"} :index
       "tags" {#{"" "/"} :tags}
       "admin" {#{"" "/"} :admin}
       "pages" {#{"" "/"} :pages}
       "posts" {#{"" "/"} :posts}
       "categories" {#{"" "/"} :categories}
       true :not-blaze}])

(defc= route (bidi/route routes))

(defn route? [handler & [prefix]]
  (bidi/route? (routes prefix) handler))

(defn mkroute [handler & {:keys [id prefix]}]
  (if-not id
    (bidi/mkroute (routes prefix) handler)
    (bidi/mkroute (routes prefix) handler :id id)))

(defn route! [handler & {:keys [id prefix]}]
  (if-not id
    (bidi/route! (routes prefix) handler)
    (bidi/route! (routes prefix) handler :id id)))
