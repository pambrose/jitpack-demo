import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    val kotlinVersion: String by System.getProperties()
    val versionsVersion: String by System.getProperties()
    kotlin("jvm") version kotlinVersion
    id("com.github.ben-manes.versions") version versionsVersion
    id("maven-publish")         // Required for publishing to jitpack.io
}

val vstr = "1.0.2"

group = "com.pambrose"
version = vstr

repositories {
    mavenCentral()
}

// Required for publishing to jitpack.io
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

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}

tasks {
    // Required for building jar file
    register<Jar>("uberJar") {
        archiveClassifier.set("uber")
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
        from({ configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) } })
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            listOf("BETA", "-RC").any { candidate.version.uppercase().contains(it) }
        }
    }

    test {
        useJUnitPlatform()
    }
}
