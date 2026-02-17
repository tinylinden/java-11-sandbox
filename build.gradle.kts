plugins {
	java
}

subprojects {
	group = "eu.tinylinden"
	version = "0.0.1-SNAPSHOT"

	apply(plugin = "java")

	repositories {
		mavenCentral()
	}

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(11)
		}
	}

	dependencies {
		testImplementation("org.junit.jupiter:junit-jupiter:5.14.3")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")
		testImplementation("org.assertj:assertj-core:3.27.7")
	}

	tasks {
		withType<Test> {
			useJUnitPlatform()
		}
	}
}
