plugins {
    id("me.champeau.jmh") version "0.7.3"
}

dependencies {
    implementation("io.vavr:vavr:0.11.0")

    jmh("org.openjdk.jmh:jmh-core:1.37")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:1.37")
}

jmh {
    resultFormat = "JSON"
}
