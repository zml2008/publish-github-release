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
package ca.stellardrift.ghrelease.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.kyori.mammoth.test.GradleFunctionalTest;
import net.kyori.mammoth.test.GradleParameters;
import net.kyori.mammoth.test.TestVariant;
import net.kyori.mammoth.test.TestVariantResource;

@GradleFunctionalTest
@GradleParameters({"--warning-mode", "fail", "--stacktrace"}) // parameters for all variants
@TestVariant(gradleVersion = "8.3", extraArguments = {"--configuration-cache"})
@TestVariantResource(value = "/injected-gradle-versions", optional = true)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
public @interface GHReleaserFunctionalTest {

}
