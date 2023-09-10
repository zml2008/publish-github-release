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

import javax.inject.Inject;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.api.provider.ProviderFactory;

class GitHubReleaserExtensionImpl implements GithubReleaserExtension {
  private final Property<String> enterpriseUrl;
  private final Property<String> apiToken;
  private final Property<String> releaseTitle;
  private final Property<String> releaseBody;
  private final Property<String> repository;
  private final Property<String> tagName;
  private final Property<String> sourceBranch;
  private final Property<Boolean> draft;
  private final Property<Boolean> prerelease;
  private final Property<String> discussionCategoryName;
  private final Property<Boolean> generateReleaseNotes;
  private final Property<LatestState> makeLatest;

  private final ConfigurableFileCollection sourceArtifacts;

  @Inject
  public GitHubReleaserExtensionImpl(final ObjectFactory objects, final ProviderFactory providers) {
    this.enterpriseUrl = objects.property(String.class);
    this.apiToken = objects.property(String.class)
      .convention(providers.environmentVariable("GITHUB_TOKEN"));
    this.releaseTitle = objects.property(String.class);
    this.releaseBody = objects.property(String.class).convention("");
    this.repository = objects.property(String.class);
    this.tagName = objects.property(String.class);
    this.sourceBranch = objects.property(String.class);
    this.draft = objects.property(Boolean.class).convention(false);
    this.prerelease = objects.property(Boolean.class).convention(false);
    this.discussionCategoryName = objects.property(String.class);
    this.generateReleaseNotes = objects.property(Boolean.class).convention(false);
    this.makeLatest = objects.property(LatestState.class).convention(LatestState.TRUE);

    this.sourceArtifacts = objects.fileCollection();
  }

  @Override
  public Property<String> getEnterpriseUrl() {
    return this.enterpriseUrl;
  }

  @Override
  public Property<String> getApiToken() {
    return this.apiToken;
  }

  @Override
  public Property<String> getReleaseName() {
    return this.releaseTitle;
  }

  @Override
  public Property<String> getReleaseBody() {
    return this.releaseBody;
  }

  @Override
  public Property<String> getRepository() {
    return this.repository;
  }

  @Override
  public Property<String> getTagName() {
    return this.tagName;
  }

  @Override
  public Property<String> getSourceBranch() {
    return this.sourceBranch;
  }

  @Override
  public Property<Boolean> getDraft() {
    return this.draft;
  }

  @Override
  public Property<Boolean> getPrerelease() {
    return this.prerelease;
  }

  @Override
  public Property<String> getDiscussionCategoryName() {
    return this.discussionCategoryName;
  }

  //@Override
  public Property<Boolean> getGenerateReleaseNotes() {
    return this.generateReleaseNotes;
  }

  @Override
  public Property<LatestState> getMakeLatest() {
    return this.makeLatest;
  }

  @Override
  public ConfigurableFileCollection getArtifacts() {
    return this.sourceArtifacts;
  }
}
