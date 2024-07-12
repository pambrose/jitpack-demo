# Using https://jitpack.io with build.gradle.kts

[![](https://jitpack.io/v/pambrose/jitpack-demo.svg)](https://jitpack.io/#pambrose/jitpack-demo)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/pambrose/jitpack-demo)
[![Kotlin version](https://img.shields.io/badge/kotlin-2.0.0-red?logo=kotlin)](http://kotlinlang.org)

## Build a jar file

1) Add the `maven-publish` plugin to the `plugins{}` section of the `build.gradle.kts` file
2) Register the `uberJar` task in the `tasks{}` section of the `build.gradle.kts` file

## Publish a jar to mavenLocal

1) `./gradlew publishToMavenLocal`

## Publish a jar to jitpack.io

The required elements for publishing on jitpack.io are:

1) The `jitpack.yml` file
2) The `publishing{}` section of the `build.gradle.kts` file
3) After pushing to GitHub, create a release for the project. The build on jitpack.io will use that tag.

## Using a jar from jitpack.io

Add the following to the `build.gradle.kts` file in the repo requesting the jar file from jitpack.io:

```kotlin
repositories {
  mavenCentral()
  mavenLocal()
  maven(url = "https://jitpack.io")
}

dependencies {
  implementation("com.pambrose:jitpack-demo:1.0.2")
}
```
