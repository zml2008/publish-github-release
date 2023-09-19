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

import org.gradle.api.provider.Property;
import org.jetbrains.annotations.NotNull;

public interface GithubReleaserExtension extends ReleaseJobParameters {
  /**
   * Get an endpoint override for GitHub.
   *
   * <p>Only required if using GitHub enterprise.</p>
   *
   * @return the base url for the GitHub instance
   */
  @NotNull Property<String> getEnterpriseUrl();

  /**
   * Get the API token used to authenticate with GitHub.
   *
   * <p>By default, this is read from the {@code GITHUB_TOKEN} environment variable.</p>
   *
   * @return the api token property
   */
  @NotNull Property<String> getApiToken();
}
