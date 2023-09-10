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

import ca.stellardrift.ghrelease.test.GHReleaserFunctionalTest;
import ca.stellardrift.ghrelease.test.GitHubReleaserDisplayNameGeneration;
import ca.stellardrift.ghrelease.test.SettingsFactory;
import java.io.IOException;
import net.kyori.mammoth.test.TestContext;
import org.junit.jupiter.api.DisplayNameGeneration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(GitHubReleaserDisplayNameGeneration.class)
class GHReleaserTest {
  @GHReleaserFunctionalTest
  void testPluginSimplyApplies(final TestContext ctx) throws IOException {
    ctx.copyInput("build.gradle");
    SettingsFactory.writeSettings(ctx, "pluginSimplyApplies");

    assertDoesNotThrow(() -> ctx.build("help"));
  }
}
