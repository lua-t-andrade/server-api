(defproject server-api "0.1.0-SNAPSHOT"
  :description "Simple server API in clojure for learning."
  :url "https://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [ring "1.8.1"] ;; HTTP server abstraction
                 [compojure "1.6.1"] ;; Routing library for Ring
                 [honeysql "1.0.444"]
                 [seancorfield/next.jdbc "1.0.13"] ;; Java Database Connectivity Wrapper
                 [org.postgresql/postgresql "42.2.10.jre7"]
                 [com.stuartsierra/component "0.4.0"]] ;; lifecycle of stateful objects management

  :main ^:skip-aot server-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
