plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.alten"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}


dependencies {
	//spring boot
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")

	//security
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.security:spring-security-crypto")
	implementation("io.jsonwebtoken:jjwt:0.12.6")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.6")
	implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")

	//db
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("mysql:mysql-connector-java:8.0.33")
	implementation("org.flywaydb:flyway-core:11.3.4")
	implementation("org.flywaydb:flyway-mysql:11.3.4")

	//mocks
	testImplementation("org.mockito:mockito-inline:3.12.4")

	//utils
	implementation("org.projectlombok:lombok:1.18.28")
	annotationProcessor("org.projectlombok:lombok:1.18.28")

	//tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation ("io.rest-assured:rest-assured:5.5.1")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
}

configurations.all {
	resolutionStrategy.eachDependency {
		if (requested.group == "org.junit.platform") {
			useVersion("1.10.0")
		}
		if (requested.group == "org.junit.jupiter") {
			useVersion("5.10.1")
		}
	}
}



tasks.test {
	useJUnitPlatform()
}
