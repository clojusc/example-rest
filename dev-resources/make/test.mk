kibit:
	@lein with-profile +test kibit

eastwood:
	@lein with-profile +test eastwood "{:namespaces [:source-paths]}"

lint: kibit eastwood

lint-unused:
	@lein eastwood "{:linters [:unused-fn-args :unused-locals :unused-namespaces :unused-private-vars :wrong-ns-form] :namespaces [:source-paths]}"

lint-ns:
	@lein eastwood "{:linters [:unused-namespaces :wrong-ns-form] :namespaces [:source-paths]}"

unit:
	@lein test :unit

system:
	@lein test :system

integration:
	@lein test :integration

check: lint
	@lein test :all

.PHONY: check lint
