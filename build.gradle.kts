plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "ru.dfhub.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.telegram:telegrambots-longpolling:7.7.1")
    implementation("org.telegram:telegrambots-client:7.7.1")
    implementation("org.json:json:20240303")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "ru.dfhub.test.MainKt"
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = Charsets.UTF_8.name()
}

tasks.processResources {
    filteringCharset = Charsets.UTF_8.name()
}
