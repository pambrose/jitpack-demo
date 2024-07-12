default: versioncheck

versioncheck:
	./gradlew dependencyUpdates

clean:
	./gradlew clean

build:
	./gradlew build -xtest

jar: clean build
	./gradlew uberJar

publish:
	./gradlew publishToMavenLocal

upgrade-wrapper:
	./gradlew wrapper --gradle-version=8.9 --distribution-type=bin
