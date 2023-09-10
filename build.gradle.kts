plugins {
  `java-gradle-plugin`
  alias(libs.plugins.indra)
  alias(libs.plugins.indra.gradlePluginPublish)
  alias(libs.plugins.indra.licenserSpotless)
  alias(libs.plugins.pluginPublish)
  alias(libs.plugins.spotless)
}

dependencies {
  api(libs.mammoth)
  compileOnlyApi(libs.jetbrainsAnnotations)
  implementation(libs.githubApi)

  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.junit.api)
  testImplementation(libs.mammoth.test)
  testRuntimeOnly(libs.junit.engine)
  testRuntimeOnly(libs.junit.launrcher)
}

indra {
  github("zml2008", "publish-github-release") {
    ci(true)
  }
  lgpl3OrLaterLicense()
  javaVersions {
    target(11)
    minimumToolchain(17)
    testWith(11, 17, 20)
  }

  configurePublications {
    pom {
      url = "https://publish-github-release.stellardrift.ca"
      developers {
        developer {
          name = "zml"
          email = "zml at stellardrift [.] ca"
        }
      }
    }
  }

  publishReleasesTo("stellardrift", "https://repo.stellardrift.ca/repository/releases/")
  publishSnapshotsTo("stellardrift", "https://repo.stellardrift.ca/repository/snapshots/")
}

tasks.jar {
  indraGit.applyVcsInformationToManifest(manifest)
}

indraPluginPublishing {
  website("https://publish-github-release.stellardrift.ca")
  plugin(
    "publish-github-release",
    "ca.stellardrift.ghrelease.GitHubReleaserPlugin",
    "GitHub Release Publisher",
    description,
    listOf("release", "publish", "github")
  )
}

spotless {
  java {
    removeUnusedImports()
    importOrderFile(rootProject.file(".spotless/stellardrift.importorder"))
    endWithNewline()
    indentWithSpaces(2)
    trimTrailingWhitespace()
  }
}

indraSpotlessLicenser {
  licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
}
