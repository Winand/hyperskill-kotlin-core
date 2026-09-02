plugins {
    kotlin("jvm") version "2.3.21"
}

group = "org.winand"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:6.1.3")
// included by junit-jupiter
//    testImplementation("org.junit.jupiter:junit-jupiter-params:6.1.3")
    testImplementation(libs.mockk)  // defined in gradle/libs.versions.toml

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
    implementation("ch.qos.logback:logback-classic:1.6.3")
    implementation("org.slf4j:slf4j-api:2.0.18")
}

kotlin {
    jvmToolchain(25)
}

// https://hyperskill.org/learn/step/24358 "This tells Gradle to use
// JUnitPlatform to run the tests, otherwise, Gradle will not be able
// to see your tests and run them."
tasks.test {
    useJUnitPlatform()

    testLogging {  // "more detailed output for the executed tests"
        events("passed", "skipped", "failed")
    }
}
