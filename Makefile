default: versioncheck

clean:
	./gradlew clean

build:
	./gradlew build -xtest

jar: clean build
	./gradlew uberJar

publish: jar
	./gradlew publishToMavenLocal

versioncheck:
	./gradlew dependencyUpdates

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.9 --distribution-type=bin
