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
    [clj-http "3.3.0"]
    [clojusc/twig "0.2.4"]
    [com.stuartsierra/component "0.3.1"]
    [compojure "1.5.1"]
    [dire "0.5.4"]
    [http-kit "2.2.0"]
    [leiningen-core "2.7.1" :exclusions [org.clojure/clojure]]
    [org.clojure/clojure "1.8.0"]
    [org.clojure/tools.cli "0.3.5"]
    [ring.middleware.logger "0.5.0" :exclusions [org.slf4j/slf4j-log4j12]]
    [ring/ring-core "1.5.0"]
    [ring/ring-defaults "0.2.1"]
    [ring/ring-devel "1.5.0"]
    [ring/ring-jetty-adapter "1.5.0"]
    [ring/ring-json "0.4.0"]]
  :source-paths ["src"]
  :main example-rest.server.app
  :rest-server {
    :host "localhost"
    :port 10008}
  :logging {
    :level :info
    :namespaces [example-rest ring onelog]}
  :profiles {
    :uberjar {
      :aot :all}
    :test {
      :plugins [
        [jonase/eastwood "0.2.3" :exclusions [org.clojure/clojure]]
        [lein-kibit "0.1.2" :exclusions [org.clojure/clojure]]]
      :test-selectors {
      :default :unit
      :unit :unit
      :system :system
      :integration :integration}}
    :doc {
      :plugins [
        [lein-codox "0.10.0"]]
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
        [org.clojure/tools.namespace "0.3.0-alpha3" :exclusions [
          org.clojure/clojure org.clojure/tools.reader]]
        [org.clojure/tools.nrepl "0.2.12"]]
      :plugins [[lein-ring "0.9.7"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns example-rest.dev}}})
