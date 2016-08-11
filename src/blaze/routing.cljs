(ns blaze.routing)

(def routes
  ["" {#{"" "/"} :index
   "tags" {#{"" "/"} :tags}
   "admin" #{"" "/"} :admin}
   "pages" {#{"" "/"} :pages}
   "posts" {#{"" "/"} :posts}
   "categories" {#{"" "/"} :categories}])
