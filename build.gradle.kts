plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}


tasks.shadowJar {
    archiveBaseName.set("app")              // Set JAR's base name
    archiveClassifier.set("")               // Set no classifier to replace the default JAR
    archiveVersion.set(version.toString())  // Include version in JAR name
    manifest {
        attributes["Main-Class"] = "org.example.CustomerController" // Main class
    }
}