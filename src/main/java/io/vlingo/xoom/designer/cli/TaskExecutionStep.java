// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.cli;

public interface TaskExecutionStep {

  default void process() {
    process(TaskExecutionContext.bare());
  }

  void process(final TaskExecutionContext context);

  default boolean shouldProcess(final TaskExecutionContext context) {
    return true;
  }

}