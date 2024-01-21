pluginManagement {
  repositories {
    maven("https://repo.stellardrift.ca/repository/internal/") {
      name = "stellardriftReleases"
      mavenContent { releasesOnly() }
    }
    maven("https://repo.stellardrift.ca/repository/internal/") {
      name = "stellardriftSnapshots"
      mavenContent { releasesOnly() }
    }
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "publish-github-release"

dependencyResolutionManagement {
  repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
  pluginManagement.repositories.forEach(repositories::add)
}
