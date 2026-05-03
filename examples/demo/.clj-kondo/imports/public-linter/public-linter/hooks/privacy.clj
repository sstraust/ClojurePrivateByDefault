;; Macroexpand hook for clojure.core/defn.
;; Marks any defn without ^:public metadata as ^:private, so clj-kondo's
;; built-in :private-call linter flags cross-namespace calls to it.
(ns hooks.privacy
  (:refer-clojure :exclude [defn]))

(defmacro defn [fn-name & body]
  (let [m (meta fn-name)
        new-meta (if (:public m) m (assoc m :private true))
        new-name (with-meta fn-name new-meta)
        body (drop-while (fn [x] (or (string? x) (map? x))) body)]
    `(def ~new-name (fn ~fn-name ~@body))))
