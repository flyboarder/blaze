(ns blaze.firebase
  (:require [cljsjs.firebase]
            [firebase-cljs.core :as fb]
            [firebase-cljs.database :as fbdb]
            [hoplon.firebase :as hfb]))

;; Firebase ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic *auth* (fb/get-auth))

(def ^:dynamic *app*  (fb/get-app))

(def ^:dynamic *db*   (fbdb/get-in (fb/get-db) [:_blaze]))

;; Firebase Public References ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn posts
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:posts])))

(defn settings
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:settings])))

(defn categories
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:categories])))

(defn tags
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:tags])))

(defn pages
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:pages])))

(defn navigation
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:navigation])))

(defn apps
  [& [db]]
  (hfb/fb-cell (fbdb/get-in (or db *db*) [:apps])))

(cell= (prn (posts)))
