// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.designer.task.projectgeneration.code.java;

import io.vlingo.xoom.designer.task.projectgeneration.code.EndToEndTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@EndToEndTest
public class ProjectWithStatefulEntitiesAndProjectableEventsGenerationTest extends ProjectGenerationTest {

  @BeforeAll
  public static void setUp() {
    init();
  }

  @Test
  public void testThatGeneratedProjectIsWorking() {
    generateProjectFor("freighter-maintenance-designer-model");
  }

  @Override
  public String modelDirectory() {
    return "freighter-maintenance-context";
  }

  @AfterAll
  public static void tearDown() throws Exception {
    clear();
  }
}