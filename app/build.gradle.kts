plugins {
    id("java")
    id("application")
    id("checkstyle")
    jacoco
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("com.h2database:h2:2.2.224")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("io.javalin:javalin:6.1.3")
    implementation("io.javalin:javalin-bundle:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("gg.jte:jte:3.1.9")

    implementation(platform("com.konghq:unirest-java-bom:4.4.5"))
    implementation("com.konghq:unirest-java-core")
    implementation("org.jsoup:jsoup:1.14.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.javalin:javalin-testtools:6.1.3")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("com.squareup.okhttp3:okhttp:4.10.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test>() {
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

application {
    mainClass.set("hexlet.code.App")
}