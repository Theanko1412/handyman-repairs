import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	//default
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
	//manual
	id("com.diffplug.spotless") version "6.25.0"
	id("org.jetbrains.kotlinx.kover") version "0.8.0"
}

group = "hr.fer.infsus"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	//default
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.liquibase:liquibase-core") temp disabled
	//manual
	implementation("org.jetbrains.kotlinx:kover-gradle-plugin:0.8.0")
	implementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.security:spring-security-web")
	implementation("org.springframework.security:spring-security-core")

	//default
	runtimeOnly("org.postgresql:postgresql")

	//default
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	//manual
	testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
	testImplementation("io.kotest:kotest-assertions-core:5.8.0")
	testImplementation("io.mockk:mockk:1.13.7")
}

spotless {
	kotlin {
		ktlint()
		endWithNewline()
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
