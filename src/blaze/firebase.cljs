(ns blaze.firebase
  (:require [cljsjs.firebase]
            [firebase-cljs.core :as fb]
            [firebase-cljs.database :as fbdb]
            [hoplon.firebase :as hfb]))

(def auth (fb/get-auth))

(def app (fb/get-app))

(def db (fb/get-db))

(def posts (hfb/fb-cell ()))
