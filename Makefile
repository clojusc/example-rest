VERSION=0.1.0
PROJECT=example-rest-api
STANDALONE=target/$(PROJECT)-$(VERSION)-SNAPSHOT-standalone.jar

clean:
	rm -rf target

build: clean
	@lein compile
	@lein uberjar

shell:
	@lein repl

dev:
	@lein run --development

run:
	@lein run

standalone: build
	java -jar $(STANDALONE)

kibit-only:
	@lein with-profile testing kibit

test-only:
	@MIDJE_COLORIZE=true lein with-profile testing midje :print-facts

coverage-only:
	@lein with-profile testing cloverage  --text --html
	@cat target/coverage/coverage.txt
	@echo "body {background-color: #000; color: #fff;} \
	a {color: #A5C0F0;}" >> target/coverage/coverage.css

check: kibit-only test-only coverage-only
