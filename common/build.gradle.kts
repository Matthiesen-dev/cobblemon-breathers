plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("matthiesen.minecraft-module-conventions")
}

architectury {
    common("neoforge", "fabric")
}

val generatedResources = file("src/generated")

sourceSets {
    main {
        resources.srcDir(generatedResources)
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.bundles.commonModImplementationNoTransitive) { isTransitive = false }
    modCompileOnly(libs.bundles.commonModCompileOnly)

    testImplementation(libs.bundles.testImplementation)
    testRuntimeOnly(libs.bundles.testRuntimeOnly)
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        filesMatching("pack.mcmeta") {
            expand(project.properties)
        }
    }
}
