(ns blaze.firebase
  (:require [cljsjs.firebase]
            [firebase-cljs.core :as fb]
            [hoplon.firebase :as hfb]))

(def posts (hfb/fb-cell))
