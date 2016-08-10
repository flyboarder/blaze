(ns blaze.firebase
  (:require [cljsjs.firebase]
            [firebase-cljs.core :as fb])
  (:require-macros [adzerk.env :as env]))

(env/def
  BLAZE_API_KEY        :required
  BLAZE_AUTH_DOMAIN    :required
  BLAZE_DATABASE_URL   :required
  BLAZE_STORAGE_BUCKET :required)

(fb/init
  {:apiKey BLAZE_API_KEY
   :authDomain BLAZE_AUTH_DOMAIN
   :databaseURL BLAZE_DATABASE_URL
   :storageBucket BLAZE_STORAGE_BUCKET})
