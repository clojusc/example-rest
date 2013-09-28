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
	@lein with-profile +test kibit

test-only:
	@MIDJE_COLORIZE=true lein with-profile testing midje :print-facts

coverage-only:
	@lein with-profile +test cloverage  --text --html
	@cat target/coverage/coverage.txt

check: kibit-only test-only coverage-only
