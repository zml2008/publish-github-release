/*
 * Copyright (c) 2023 zml and contributors
 * This file is part of publish-github-release.
 *
 * publish-github-release is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * publish-github-release is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with publish-github-release.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.stellardrift.ghrelease;

import net.kyori.mammoth.ProjectPlugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.tasks.TaskContainer;
import org.jetbrains.annotations.NotNull;

public class GitHubReleaserPlugin implements ProjectPlugin {
  public static final String GITHUB_RELEASE_EXTENSION_NAME = "githubRelease";
  public static final String GITHUB_RELEASE_TASK_NAME = "publishToGitHub";

  private static final String TAG_REF_PREFIX = "refs/tags/";


  @Override
  public void apply(@NotNull Project project, @NotNull PluginContainer plugins, @NotNull ExtensionContainer extensions, @NotNull TaskContainer tasks) {
    // extension
    final GithubReleaserExtension extension = extensions.create(
      GithubReleaserExtension.class,
      GITHUB_RELEASE_EXTENSION_NAME,
      GitHubReleaserExtensionImpl.class
    );

    this.configureTasks(tasks, extension);
    this.registerPublishTask(tasks, extension);

    // Extract the appropriate ref when running in GitHub Actions
    extension.getTagName().convention(project.getProviders().environmentVariable("GITHUB_REF").map(ref -> {
      if (!ref.startsWith(TAG_REF_PREFIX)) {
        return null;
      }
      return ref.substring(TAG_REF_PREFIX.length());
    }));
  }

  private void configureTasks(final TaskContainer tasks, final GithubReleaserExtension extension) {
    tasks.withType(PublishGitHubRelease.class).configureEach(task -> {
      task.getEnterpriseUrl().set(extension.getEnterpriseUrl());
      task.getApiToken().set(extension.getApiToken());
    });
  }

  private void registerPublishTask(final TaskContainer tasks, final ReleaseJobParameters sourceParameters) {
    tasks.register(GITHUB_RELEASE_TASK_NAME, PublishGitHubRelease.class, task -> {
      task.getReleaseName().set(sourceParameters.getReleaseName());
      task.getReleaseBody().set(sourceParameters.getReleaseBody());
      task.getRepository().set(sourceParameters.getRepository());
      task.getTagName().set(sourceParameters.getTagName());
      task.getSourceBranch().set(sourceParameters.getSourceBranch());
      task.getDraft().set(sourceParameters.getDraft());
      task.getPrerelease().set(sourceParameters.getPrerelease());
      task.getDiscussionCategoryName().set(sourceParameters.getDiscussionCategoryName());
      // task.getGenerateReleaseNotes().set(sourceParameters.getGenerateReleaseNotes());
      task.getMakeLatest().set(sourceParameters.getMakeLatest());
      task.getArtifacts().from(sourceParameters.getArtifacts());
    });
  }
}
