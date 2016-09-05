lint:
	@lein kibit
	@lein eastwood "{:namespaces [:source-paths]}"

lint-unused:
	@lein eastwood "{:linters [:unused-fn-args :unused-locals :unused-namespaces :unused-private-vars :wrong-ns-form] :namespaces [:source-paths]}"

lint-ns:
	@lein eastwood "{:linters [:unused-namespaces :wrong-ns-form] :namespaces [:source-paths]}"

check: lint
	lein test
