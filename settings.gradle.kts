pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://jitpack.io") }
    }
}
buildscript {
    extra.apply {
        set("androidxMediaModulePrefix", "media-")
    }
}

apply(from = file("E:\\media3\\media-release\\core_settings.gradle"))

rootProject.name = "HotelTVAppTemplate"
include(":app")
