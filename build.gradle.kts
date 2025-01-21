plugins {
    id("java")
}

group = "me.lsam.ddunigma"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}