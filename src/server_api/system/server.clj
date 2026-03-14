(ns server-api.system.server
  (:require [ring.adapter.jetty :as jetty]
            [com.stuartsierra.component :as component]))

;; HTTP server component.

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello world! Viva!!"})

(defn start-server
  "Helper function to start server."
  [port]
  (let [server
        (jetty/run-jetty handler
                         {:port (Integer. port)})]
    server))

(defn stop-server
  "Helper function to stop server."
  [server]
  (when server
    (dissoc server :server)))

(defrecord WebServer [port]
  ;; Record for handling the helper functions.
  ;; Lifecycle of the server.
  component/Lifecycle
  (start [this]
    (assoc this :server (start-server port)))
  (stop [this]
    (stop-server (:server this))
    (dissoc this :server)))

(defn web-server
  "Maps the web server to the component."
  [port]
  (map->WebServer {:port port}))
