// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.cli;

import io.vlingo.xoom.cli.task.TaskExecutionException;

public class CommandNotFoundException extends TaskExecutionException {
  private static final long serialVersionUID = 8539654805622498779L;

  public CommandNotFoundException() {
      super("Please inform a command.");
  }

}
