plugins {
    val versionsVersion: String by System.getProperties()
    kotlin("jvm") version "2.0.0"
    id("maven-publish")
    id("com.github.ben-manes.versions") version versionsVersion
}

val vstr = "1.0.2"

group = "com.pambrose"
version = vstr

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.pambrose"
            artifactId = "jitpack-demo"
            version = vstr
            from(components["java"])
        }
    }
}


tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("uberJar") {
    archiveClassifier.set("uber")
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({ configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) } })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
