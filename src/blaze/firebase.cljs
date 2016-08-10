(ns blaze.firebase
  (:require [cljsjs.firebase]
            [firebase-cljs.core :as fb]
            [firebase-cljs.database :as fbdb]
            [hoplon.firebase :as hfb]))

(def auth (fb/get-auth))

(def app (fb/get-app))

(def db (fb/get-db))

(def posts (hfb/fb-cell (fbdb/get-in db [:posts])))

(def settings (hfb/fb-cell (fbdb/get-in db [:settings])))

(def categories (hfb/fb-cell (fbdb/get-in db [:categories])))

(def tags (hfb/fb-cell (fbdb/get-in db [:tags])))

(def pages (hfb/fb-cell (fbdb/get-in db [:pages])))

(def navigation (hfb/fb-cell (fbdb/get-in db [:navigation])))

(def apps (hfb/fb-cell (fbdb/get-in db [:apps])))
