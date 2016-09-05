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
    [ring/ring-devel "1.2.0"]
    [ring/ring-core "1.2.0"]]
  :plugins [[lein-ring "0.8.5"]
            [lein-ring "0.9.7"]]
  :ring {:handler example-rest-api.handler/app}
  :main example-rest-api.main
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
        [org.clojure/tools.namespace "0.2.11"]
        [lein-simpleton "1.3.0"]]
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns coati.dev}}})
