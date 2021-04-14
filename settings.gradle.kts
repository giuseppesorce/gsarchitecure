dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "GSArchitecture"
include(":app")

include(":domain")
include(":data")
include(":network")
include(":architecture")
include(":network")
include(":persistence")
