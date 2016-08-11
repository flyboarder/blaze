(ns blaze.firebase
  (:require [cljsjs.firebase]
            [firebase-cljs.core :as fb]
            [firebase-cljs.database :as fbdb]
            [hoplon.firebase :as hfb]))

;; Firebase ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic auth (fb/get-auth))

(def ^:dynamic app  (fb/get-app))

(def ^:dynamic db   (fbdb/get-in (fb/get-db) [:_blaze])

;; Firebase Public References ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def posts      (hfb/fb-cell (fbdb/get-in db [:posts])))

(def settings   (hfb/fb-cell (fbdb/get-in db [:settings])))

(def categories (hfb/fb-cell (fbdb/get-in db [:categories])))

(def tags       (hfb/fb-cell (fbdb/get-in db [:tags])))

(def pages      (hfb/fb-cell (fbdb/get-in db [:pages])))

(def navigation (hfb/fb-cell (fbdb/get-in db [:navigation])))

(def apps       (hfb/fb-cell (fbdb/get-in db [:apps])))
