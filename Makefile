VERSION=0.1.0
PROJECT=example-rest-api
STANDALONE=target/$(PROJECT)-$(VERSION)-SNAPSHOT-standalone.jar

build:
	@lein compile
	@lein uberjar

clean:
	rm -rf target

shell:
	@lein repl

run: build
	java -jar $(STANDALONE)

kibit-only:
	@lein with-profile +test kibit

test-only:
	@MIDJE_COLORIZE=true lein with-profile testing midje :print-facts

check: kibit-only test-only
	@lein with-profile +test cloverage  --text --html
	@cat target/coverage/coverage.txt
