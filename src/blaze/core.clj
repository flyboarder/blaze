(ns blaze.core)

(defn- partition-let [conf]
  (->> conf
      (map
        (fn [[l b e]]
          (cond (and l b) [l b]
                (and l e) [l e])))
      (remove nil?)
      (reduce concat)
      vec))

(defn- partition-bindings [conf]
  (->> conf
      (map
        (fn [[l b e]]
          (when b [b e])))
      (remove nil?)
      (reduce concat)
      vec))

(defmacro config
  "Sugar for a combination of `binding` and `let`. Takes a set of bindings, in
  triplets as `conf` and a `body` expression. `conf` must be a multiple of 3,
  not a vector of vectors. First generates bindings for `binding` then for `let`.

  Bindings generated for `binding` are thread local and cannot refer to eachother.

  Bindings can be `let` or `binding` only:
    let-only:     [let nil exp]
    binding-only: [nil binding exp]

  `let` only bindings may refer to past `let` or `binding` bindings.

  Bindings are most convenient as `let` of `binding`:
    let-of-binding: [let binding expression]

  This allows you to set a dynamic binding and refer to it locally simultaneously."
  [conf & [body]]
  {:pre (mod (count conf) 3)}
  (let [conf (partition 3 conf)
        bindings (partition-bindings conf)
        lets (partition-let conf)]
    `(binding ~bindings
      (let ~lets
        ~body))))
