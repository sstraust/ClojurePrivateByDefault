(ns public-linter.core
  (:gen-class))

(defn ^:public greet [name]
  (str "Hello, " name "!"))

(defn shout [s]
  (str s "!!!"))

(defn ^:public -main [& _args]
  (println (greet "world"))
  (println (shout "internal use is fine")))
