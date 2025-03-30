plugins {
    kotlin("plugin.serialization") version "1.9.0"
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}