(ns datalevin-component.mock-test
  (:require [clojure.test :refer :all]
            [datalevin-component.mock :as database.mock]
            [datalevin.core :as d]
            [schema.test :as s]))

(s/deftest database-connection-for-unit-tests-test
  (testing "We should be able to create a database connection and use it to transact entities and query them"
    (let [schema {:name {:db/valueType :db.type/string
                         :db/unique    :db.unique/identity}}
          database-connection (database.mock/database-connection-for-unit-tests! schema)]
      (d/transact! database-connection [{:name "Frege"}])
      (is (= [[{:db/id 1 :name "Frege"}]]
             (d/q '[:find (pull ?e [*])
                    :in $ ?name
                    :where
                    [?e :name ?name]] (d/db database-connection) "Frege"))))))
