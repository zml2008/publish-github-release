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

import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Optional;
import org.jetbrains.annotations.NotNull;

public interface ReleaseJobParameters {
  @Input
  @Optional // if it's not present, just use the tag name
  @NotNull Property<String> getReleaseName();

  @Input
  @NotNull Property<String> getReleaseBody();

  @Input
  @NotNull Property<String> getRepository();

  @Input
  @NotNull Property<String> getTagName(); // defaults to trimmed GITHUB_REF

  @Input
  @Optional
  @NotNull Property<String> getSourceBranch(); // if set, will create a tag with the provided name

  @Input
  @NotNull Property<Boolean> getDraft();

  @Input
  @NotNull Property<Boolean> getPrerelease();

  @Input
  @Optional
  @NotNull Property<String> getDiscussionCategoryName();

  //@Input
  //Property<Boolean> getGenerateReleaseNotes(); // todo

  @Input
  @NotNull Property<LatestState> getMakeLatest();

  @InputFiles
  @NotNull ConfigurableFileCollection getArtifacts();

  enum LatestState {
    TRUE,
    FALSE,
    LEGACY
  }
}
