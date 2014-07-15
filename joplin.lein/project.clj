(defproject joplin.lein "0.1.0"
  :description "Flexible datastore migrations and seeds"
  :url "http://github.com/martintrojer/joplin"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[leinjacker "0.4.1"]

                 ;; remove
                 [com.h2database/h2 "1.3.160"]]
  :eval-in-leiningen true

  :source-paths ["src" "joplin"]

  ;; Example usage
  :joplin {
           :migrators {
                       :pmig "joplin/migrators/psql"
                       :pes "joplin/migrators/es"
                       }
           :seeds {
                   :pseed "seeds.psql/run"
                   :zseed "seeds.zk/run"
                   :eseed "seeds.es/run"
                   }
           :databases {
                       :psql-dev {:type :jdbc,
                                  :url "jdbc:h2:file:test_db"
                                  ;;"jdbc:h2:mem:test_db"
                                  }
                       :es-dev   {:type :es, :host "localhost", :port 9300, :cluster "dev"}
                       :zk-dev   {:type :zk, :host "localhost", :port 2181}
                       }
           :environments {
                          :dev     [{:db :psql-dev, :migrator :pmig, :seed :pseed}
                                    ;;{:db :es-dev, :migrator :pes}
                                    {:db :zk-dev, :seed :zseed}
                                    ]
                          }
           }
  )