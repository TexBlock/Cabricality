plugins {
    base
    java
    `maven-publish`
    alias(libs.plugins.quilt.loom)
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
}

group = property("mavenGroup").toString()
version = property("modpackVersion").toString()

base {
    archivesName.set("${property("archivesName")}-${property("moduleBaseName")}")
}

repositories {
    maven {
        name = "Modrinth Maven"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }

    maven {
        name = "Curse Maven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    }

    maven { url = uri("https://mvn.devos.one/snapshots/") }

    maven {
        name = "JitPack"
        url = uri("https://jitpack.io/")
    }

    maven {
        url = uri("https://maven.ladysnake.org/releases")
        content {
            includeGroup("io.github.ladysnake")
            includeGroup("org.ladysnake")
            includeGroupByRegex("dev\\.onyxstudios.*")
        }
    }

    maven {
        url = uri("https://maven.jamieswhiteshirt.com/libs-release")
        content {
            includeGroup("com.jamieswhiteshirt")
        }
    }

    maven { url = uri("https://aperlambda.github.io/maven") }
    maven { url = uri("https://dvs1.progwml6.com/files/maven/") }
    maven { url = uri("https://maven.cafeteria.dev/releases/") }
    maven { url = uri("https://maven.fabricmc.net/") }
    maven { url = uri("https://maven.gegy.dev") }
    maven { url = uri("https://maven.kotlindiscord.com/repository/terraformers/") }
    maven { url = uri("https://maven.parchmentmc.org") }
    maven { url = uri("https://maven.shedaniel.me/") }
    maven { url = uri("https://maven.terraformersmc.com/releases/") }
    maven { url = uri("https://maven.tterrag.com/") }
    maven { url = uri("https://maven.wispforest.io") }
    maven { url = uri("https://modmaven.dev") }
    maven { url = uri("https://raw.githubusercontent.com/SolidBlock-cn/mvn-repo/main") }
    maven { url = uri("https://storage.googleapis.com/devan-maven/") }

    mavenCentral()
}

dependencies {
    // Minecraft
    minecraft(libs.minecraft)

    // Mappings
    mappings(
        variantOf(libs.quilt.mappings) { classifier("intermediary-v2") }
    )

    // Quilt
    modImplementation(libs.quilt.loader)
    modImplementation(libs.quilted.fabric.api)

    // Parent
    implementation(rootProject)
}

tasks {
    processResources {
        inputs.property("modpackVersion", version)

        filesMatching("quilt.mod.json") {
            expand(mapOf("modpack_version" to version))
        }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${base.archivesName}" }
        }
    }
}

java {
    withSourcesJar()
    setSourceCompatibility(JavaVersion.VERSION_17)
    setTargetCompatibility(JavaVersion.VERSION_17)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
    }
}
