// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.codegen.java.model.valueobject;

import io.vlingo.xoom.codegen.CodeGenerationContext;
import io.vlingo.xoom.codegen.CodeGenerationStep;
import io.vlingo.xoom.codegen.TextExpectation;
import io.vlingo.xoom.codegen.content.Content;
import io.vlingo.xoom.codegen.dialect.Dialect;
import io.vlingo.xoom.codegen.parameter.CodeGenerationParameter;
import io.vlingo.xoom.codegen.parameter.CodeGenerationParameters;
import io.vlingo.xoom.designer.codegen.CodeGenerationTest;
import io.vlingo.xoom.designer.codegen.Label;
import io.vlingo.xoom.designer.codegen.java.JavaTemplateStandard;
import io.vlingo.xoom.designer.codegen.java.model.ValueObjectGenerationStep;
import io.vlingo.xoom.designer.codegen.java.storage.StorageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ValueObjectGenerationStepTest extends CodeGenerationTest {

  @Test
  public void testThatValueObjectsAreGenerated() {
    final CodeGenerationParameters parameters =
            CodeGenerationParameters.from(CodeGenerationParameter.of(Label.PACKAGE, "io.vlingo.xoomapp"),
                    CodeGenerationParameter.of(Label.STORAGE_TYPE, StorageType.STATE_STORE),
                    CodeGenerationParameter.of(Label.DIALECT, Dialect.JAVA),
                    authorAggregate(), bookAggregate(), nameValueObject(), rankValueObject());

    final CodeGenerationContext context = CodeGenerationContext.with(parameters);

    final CodeGenerationStep codeGenerationStep = new ValueObjectGenerationStep();

    Assertions.assertTrue(codeGenerationStep.shouldProcess(context));

    codeGenerationStep.process(context);

    Assertions.assertEquals(2, context.contents().size());

    final Content nameValueObject = context.findContent(JavaTemplateStandard.VALUE_OBJECT, "Name");
    final Content rankValueObject = context.findContent(JavaTemplateStandard.VALUE_OBJECT, "Rank");

    Assertions.assertTrue(nameValueObject.contains(TextExpectation.onJava().read("name-value-object")));
    Assertions.assertTrue(rankValueObject.contains(TextExpectation.onJava().read("rank-value-object")));
  }

  private CodeGenerationParameter authorAggregate() {
    final CodeGenerationParameter idField =
            CodeGenerationParameter.of(Label.STATE_FIELD, "id")
                    .relate(Label.FIELD_TYPE, "String");

    final CodeGenerationParameter nameField =
            CodeGenerationParameter.of(Label.STATE_FIELD, "name")
                    .relate(Label.FIELD_TYPE, "Name");

    final CodeGenerationParameter rankField =
            CodeGenerationParameter.of(Label.STATE_FIELD, "rank")
                    .relate(Label.FIELD_TYPE, "Rank");

    final CodeGenerationParameter authorRegisteredEvent =
            CodeGenerationParameter.of(Label.DOMAIN_EVENT, "AuthorRegistered")
                    .relate(idField).relate(nameField);

    final CodeGenerationParameter authorRankedEvent =
            CodeGenerationParameter.of(Label.DOMAIN_EVENT, "AuthorRanked")
                    .relate(idField).relate(rankField);

    final CodeGenerationParameter factoryMethod =
            CodeGenerationParameter.of(Label.AGGREGATE_METHOD, "withName")
                    .relate(Label.METHOD_PARAMETER, "name")
                    .relate(Label.FACTORY_METHOD, "true")
                    .relate(authorRegisteredEvent);

    final CodeGenerationParameter rankMethod =
            CodeGenerationParameter.of(Label.AGGREGATE_METHOD, "changeRank")
                    .relate(Label.METHOD_PARAMETER, "rank")
                    .relate(authorRankedEvent);

    return CodeGenerationParameter.of(Label.AGGREGATE, "Author")
            .relate(idField).relate(nameField).relate(rankField)
            .relate(factoryMethod).relate(rankMethod)
            .relate(authorRegisteredEvent).relate(authorRankedEvent);
  }


  private CodeGenerationParameter bookAggregate() {
    final CodeGenerationParameter idField =
            CodeGenerationParameter.of(Label.STATE_FIELD, "id")
                    .relate(Label.FIELD_TYPE, "String");

    final CodeGenerationParameter nameField =
            CodeGenerationParameter.of(Label.STATE_FIELD, "title")
                    .relate(Label.FIELD_TYPE, "String");

    final CodeGenerationParameter rankField =
            CodeGenerationParameter.of(Label.STATE_FIELD, "rank")
                    .relate(Label.FIELD_TYPE, "Rank");

    return CodeGenerationParameter.of(Label.AGGREGATE, "Book")
            .relate(idField).relate(nameField).relate(rankField);
  }

  private CodeGenerationParameter nameValueObject() {
    return CodeGenerationParameter.of(Label.VALUE_OBJECT, "Name")
            .relate(CodeGenerationParameter.of(Label.VALUE_OBJECT_FIELD, "firstName")
                    .relate(Label.FIELD_TYPE, "String"))
            .relate(CodeGenerationParameter.of(Label.VALUE_OBJECT_FIELD, "lastName")
                    .relate(Label.FIELD_TYPE, "String"));
  }

  private CodeGenerationParameter rankValueObject() {
    return CodeGenerationParameter.of(Label.VALUE_OBJECT, "Rank")
            .relate(CodeGenerationParameter.of(Label.VALUE_OBJECT_FIELD, "points")
                    .relate(Label.FIELD_TYPE, "int"))
            .relate(CodeGenerationParameter.of(Label.VALUE_OBJECT_FIELD, "classification")
                    .relate(Label.FIELD_TYPE, "String"));
  }
}
