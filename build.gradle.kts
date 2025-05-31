plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
}

allprojects {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven {
            name = "GitHubDkluskeHealthKMP"
            url = uri("https://maven.pkg.github.com/dkluske/HealthKMP")
            credentials {
                username =
                    System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as? String
                password =
                    System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key") as? String
            }
        }
    }
}