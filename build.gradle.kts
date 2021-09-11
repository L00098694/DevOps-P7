plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Use JUnit test framework
    testImplementation("io.cucumber:cucumber-junit:6.10.3")
    testImplementation("io.cucumber:cucumber-java:6.10.3")
    testImplementation("org.junit.vintage:junit-vintage-engine:5.7.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
    }
}
