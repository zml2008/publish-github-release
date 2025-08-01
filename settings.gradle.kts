pluginManagement {
  repositories {
    maven("https://repo.stellardrift.ca/maven/internal/") {
      name = "stellardriftReleases"
      mavenContent { releasesOnly() }
    }
    maven("https://repo.stellardrift.ca/maven/internal/") {
      name = "stellardriftSnapshots"
      mavenContent { releasesOnly() }
    }
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "publish-github-release"

dependencyResolutionManagement {
  repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
  pluginManagement.repositories.forEach(repositories::add)
}