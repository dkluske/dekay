rootProject.name = "dekay"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencyResolutionManagement {
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
            name = "GitHubPackagesDkluskeHealthKMP"
            url = uri("https://maven.pkg.github.com/dkluske/HealthKMP")
            
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user")?.toString()
                password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.key")?.toString()
            }
        }
    }
}

include(":composeApp")
