(ns server-api.system.server
  (:require [ring.adapter.jetty :as jetty]
            [com.stuartsierra.component :as component]
            [compojure.core :as compojure]
            [compojure.route :as compojure-route]))

;; HTTP server component.

(compojure/defroutes app ;; This will define all the routes for the server!!
  (compojure/GET "/" [] "<h1>WELCOME TO THE HOME PAGE! MUAHAHA</h1>")
  (compojure/GET "/test" [] "<h1>This is a test. Wow!</h1>")
  (compojure-route/not-found "<h1>HUH? PAGE NOT FOUND.</h1>"))

(defn start-server
  "Helper function to start server."
  [port]
  (let [server
        (jetty/run-jetty app
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
