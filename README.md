# publish-github-release

This is a Gradle plugin (currently working but very un-polished) to publish GitHub releases with artifacts from a Gradle build.

## Usage

Apply the plugin:

```kotlin
plugins {
  id("ca.stellardrift.publish-github-release") version "0.1.0"
}
```

This plugin requires a semi-modern version of Gradle (we only really test on 8.x), and Java 11 to run.

A simple configuration looks something like this:

```kotlin
githubRelease {
  val changelogFile = project.findProperty("changelog")
  apiToken = providers.gradleProperty("githubToken")
    .orElse(providers.environmentVariable("GITHUB_TOKEN"))

  // tag is inferred from the head tag (see below)
  repository = "user/project"
  releaseName = "MyProject v$version"
  releaseBody = project.provider { changelogFile?.let(::file)?.readText(Charsets.UTF_8) }
  artifacts.from(tasks.remapJar)
}
```

Currently we fail if a release already exists -- a good feature PR would be to allow adding files to an existing release.

### Determining the tag

By default, this plugin will only pick up tags when run from a GitHub event via the `GITHUB_REF` environment variable. 
Any Gradle plugin that provides git integration can be used to automatically provide a tag for releasing against. 
For example, with [indra-git](https://github.com/KyoriPowered/indra/wiki/indra-git):

```kotlin
githubRelease.tagName.set(project.provider {
  indraGit.headTag()?.run { org.eclipse.jgit.lib.Repository.shortenRefName(name) }
})
```

## Contributing

We'd welcome PRs expanding on functionality or improving documentation! The `publish-github-release` plugin is licensed under the GNU LGPL v3 or later, and all contributions must be released under these terms.