plugins {
    java
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "com.dcman58.Game2d.Game"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.dcman58.Game2d.Game"
    }
    from(sourceSets.main.get().output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
