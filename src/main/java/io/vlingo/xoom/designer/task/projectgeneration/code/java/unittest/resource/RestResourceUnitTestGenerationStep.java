// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package io.vlingo.xoom.designer.task.projectgeneration.code.java.unittest.resource;

import io.vlingo.xoom.codegen.CodeGenerationContext;
import io.vlingo.xoom.codegen.parameter.CodeGenerationParameter;
import io.vlingo.xoom.codegen.template.TemplateData;
import io.vlingo.xoom.codegen.template.TemplateProcessingStep;
import io.vlingo.xoom.designer.task.projectgeneration.Label;

import java.util.List;
import java.util.stream.Collectors;

public class RestResourceUnitTestGenerationStep extends TemplateProcessingStep {

  @Override
  protected List<TemplateData> buildTemplatesData(final CodeGenerationContext context) {
    final List<CodeGenerationParameter> valueObjects =
        context.parametersOf(Label.VALUE_OBJECT).collect(Collectors.toList());

    return RestResourceUnitTestTemplateDataFactory.build(context.parameters(), context.contents(), valueObjects);
  }
}