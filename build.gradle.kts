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
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.8.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}