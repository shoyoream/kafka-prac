import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.1.7"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    kotlin("plugin.allopen") version "1.3.71"
    kotlin("plugin.noarg") version "1.3.71"

    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    id("org.jlleitschuh.gradle.ktlint-idea") version "11.3.2"
}

group = "shoyoream.server"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("kotlin-spring")
        plugin("io.spring.dependency-management")
        apply(plugin = "org.jlleitschuh.gradle.ktlint")
    }

    group = "planningo.server"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.apache.kafka:kafka-streams")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.kafka:spring-kafka")
        runtimeOnly("com.mysql:mysql-connector-j")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.kafka:spring-kafka-test")
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        additionalEditorconfigFile.set(file(".editorconfig"))

        filter {
            exclude("./.gradle/**")
            include("**/kotlin/**")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.register("prepareKotlinBuildScriptModel")

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":core") {
    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    jar.enabled = true
    bootJar.enabled = false
}

project(":application") {
    tasks.bootJar {
        archiveFileName.set("application.jar")
    }
}

val jar: Jar by tasks
val bootJar: BootJar by tasks

jar.enabled = true
bootJar.enabled = false
