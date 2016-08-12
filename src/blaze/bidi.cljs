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

(def route (bidi/route routes))

(defn route? [handler]
  (bidi/route? routes handler))

(defn mkroute [handler & {:keys [id]}]
  (if-not id
    (bidi/mkroute routes handler)
    (bidi/mkroute routes handler :id id)))

(defn route! [handler & {:keys [id]}]
  (if-not id
    (bidi/route! routes handler)
    (bidi/route! routes handler :id id)))
