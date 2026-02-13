(ns datalevin.component
  (:require [clojure.tools.logging :as log]
            [datalevin.core :as d]
            [integrant.core :as ig]))

(defmethod ig/init-key ::datalevin
  [_ {:keys [components schema]}]
  (log/info :starting ::datalevin)
  (let [datalevin-uri (-> components :config :datalevin-uri)]
    (d/get-conn datalevin-uri schema)))

(defmethod ig/halt-key! ::datalevin
  [_ datalevin-connection]
  (log/info :stopping ::datalevin)
  (d/close datalevin-connection))
