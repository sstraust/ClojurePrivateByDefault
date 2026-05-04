;; Macroexpand hook for clojure.core/defn.
;; Marks any defn without ^:public metadata as ^:private, so clj-kondo's
;; built-in :private-call linter flags cross-namespace calls to it.
;; Returns a clojure.core/defn form; clj-kondo's per-symbol :visited guard
;; prevents the hook from re-firing on its own output.
(ns hooks.privacy
  (:refer-clojure :exclude [defn]))

(defmacro defn [fn-name & body]
  (let [m (meta fn-name)
        new-meta (if (:public m) m (assoc m :private true))
        new-name (with-meta fn-name new-meta)]
    `(clojure.core/defn ~new-name ~@body)))
