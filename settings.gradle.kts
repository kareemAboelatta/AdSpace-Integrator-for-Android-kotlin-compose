pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            setUrl("https://dl.gamifyspace.com/gamify/")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://dl.gamifyspace.com/gamify/")
            url = java.net.URI.create("https://dl.gamifyspace.com/gamify/")
        }
    }
}

rootProject.name = "Alyfy_App"
include(":app")
 