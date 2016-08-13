(ns blaze.bidi
  (:require [hoplon.bidi :as bidi]))

(def ^:dynamic *prefix* "_blaze")

(defn routes [& [prefix]]
  [(or prefix *prefix*)
    {#{"" "/"} :index
       "/admin" {#{"" "/"} :admin}
       "/tag" {#{"" "/"} :tag
               ["/" :id] :tag-id}
       "/post" {#{"" "/"} :post
                ["/" :id] :post-id}
       "/category" {#{"" "/"} :category
                    ["/" :id] :category-id}
       ["/" :id] :page}])

(defn route [& [prefix]]
  (let [prefix (or prefix *prefix*)]
    (bidi/route (routes prefix))))

(defn route? [handler & [prefix]]
  (let [prefix (or prefix *prefix*)]
    (bidi/route? (routes prefix) handler)))

(defn mkroute [handler & {:keys [id prefix]}]
  (let [prefix (or prefix *prefix*)]
    (if-not id
      (bidi/mkroute (routes prefix) handler)
      (bidi/mkroute (routes prefix) handler :id id))))

(defn route! [handler & {:keys [id prefix]}]
  (let [prefix (or prefix *prefix*)]
    (if-not id
      (bidi/route! (routes prefix) handler)
      (bidi/route! (routes prefix) handler :id id))))
