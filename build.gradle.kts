plugins {
    kotlin("jvm") version "1.6.10"
    java
}

group = "de.nicostraube.casacontrol"
version = "1.0.0"


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = "5.8.2")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

