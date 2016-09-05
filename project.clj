(defproject clojusc/example-rest "0.1.0-SNAPSHOT"
  :description "An Example REST API Server for Clojure"
  :url "https://github.com/clojusc/example-rest"
  :scm {
    :name "git"
    :url  "https://github.com/clojusc/example-rest"}
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [org.clojure/tools.cli "0.2.4"]
    [clojusc/twig "0.2.3"]
    [clj-http "0.7.7"]
    [compojure "1.1.5"]
    [http-kit "2.1.11"]
    [com.stuartsierra/component "0.3.1"]
    [ring.middleware.logger "0.5.0" :exclusions [org.slf4j/slf4j-log4j12]]
    [ring/ring-core "1.5.0"]
    [ring/ring-defaults "0.2.1"]
    [ring/ring-devel "1.5.0"]
    [ring/ring-jetty-adapter "1.5.0"]
    [ring/ring-json "0.4.0"]
    [leiningen-core "2.7.0"]]
  :main example-rest.server.app
  :rest-server {
    :host "localhost"
    :port 10008}
  :logging {
    :level :info
    :namespaces [example-rest ring onelog]}
  :profiles {
    :uber {
      :aot :all}
    :test {
      :test-selectors {
      :default :unit
      :unit :unit
      :system :system
      :integration :integration}}
    :doc {
      :plugins [
        [lein-codox "0.9.5"]]
      :codox {
        :project {:name "example-rest"}
        :output-path "docs/master/current"
        :doc-paths ["docs/source"]
        :namespaces [#"^example-rest-api\."]
        :metadata {
          :doc/format :markdown
          :doc "Documentation forthcoming"}}}
    :dev {
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :plugins [[lein-ring "0.9.7"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns example-rest.dev}}})
