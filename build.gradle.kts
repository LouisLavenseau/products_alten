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
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation ("io.rest-assured:rest-assured:5.5.1")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("mysql:mysql-connector-java")


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
