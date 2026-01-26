(ns datalevin-component.mock
  (:require [datalevin.core :as d]))

(defn database-connection-for-unit-tests!
  [schema]
  (d/get-conn (str "/tmp/datalevin/" (random-uuid)) schema))