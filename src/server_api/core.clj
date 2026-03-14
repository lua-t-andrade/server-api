(ns server-api.core
  (:require [server-api.system.server :as server]
            [com.stuartsierra.component :as component])
  (:gen-class))

(defn -main
  "The main function."
  [port]
  (-> (component/start
       (component/system-map :web-server ;; Hash-map of all active components
                             (server/web-server port)))))


