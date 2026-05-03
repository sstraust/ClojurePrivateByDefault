(ns public-linter.other
  (:require [public-linter.core :as core]
            [clojure.string :as str]))

(defn ^:public run []
  (core/greet "ok")               ; public, allowed
  (core/shout "should warn")      ; implicit-private, should warn
  (str/join ", " ["a" "b" "c"]))  ; external dep, should NOT warn
