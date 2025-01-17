// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.codegen.java.dataobject;

import io.vlingo.xoom.codegen.CodeGenerationContext;
import io.vlingo.xoom.codegen.dialect.Dialect;
import io.vlingo.xoom.codegen.parameter.CodeGenerationParameter;
import io.vlingo.xoom.codegen.template.TemplateData;
import io.vlingo.xoom.codegen.template.TemplateProcessingStep;
import io.vlingo.xoom.designer.codegen.Label;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DataObjectGenerationStep extends TemplateProcessingStep {

  @Override
  protected List<TemplateData> buildTemplatesData(final CodeGenerationContext context) {
    final List<CodeGenerationParameter> valueObjects =
            context.parametersOf(Label.VALUE_OBJECT).collect(toList());

    final List<TemplateData> stateDataObjectTemplateData =
            StateDataObjectTemplateData.from(context.parameterOf(Label.PACKAGE),
                    context.parameterOf(Label.DIALECT, Dialect::valueOf),
                    context.parametersOf(Label.AGGREGATE),
                    valueObjects, context.contents());

    final List<TemplateData> valueDataObjectTemplateData =
            ValueDataObjectTemplateData.from(context.parameterOf(Label.PACKAGE),
                    context.parameterOf(Label.DIALECT, Dialect::valueOf),
                    valueObjects, context.contents());

    return Stream.of(stateDataObjectTemplateData, valueDataObjectTemplateData)
            .flatMap(List::stream).collect(toList());
  }

}
