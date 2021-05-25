// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.task.projectgeneration.code.java.unittest.entitty;

import io.vlingo.xoom.codegen.content.CodeElementFormatter;
import io.vlingo.xoom.codegen.content.Content;
import io.vlingo.xoom.codegen.content.ContentQuery;
import io.vlingo.xoom.codegen.parameter.CodeGenerationParameter;
import io.vlingo.xoom.codegen.template.TemplateData;
import io.vlingo.xoom.codegen.template.TemplateParameters;
import io.vlingo.xoom.codegen.template.TemplateStandard;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.JavaTemplateStandard;
import io.vlingo.xoom.designer.task.projectgeneration.Label;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.TemplateParameter;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.model.aggregate.AggregateDetail;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.model.valueobject.ValueObjectDetail;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.projections.ProjectionType;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.storage.PersistenceDetail;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.storage.StorageType;
import io.vlingo.xoom.designer.task.projectgeneration.code.java.unittest.TestDataValueGenerator;
import io.vlingo.xoom.symbio.BaseEntry;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.vlingo.xoom.codegen.content.CodeElementFormatter.qualifiedNameOf;
import static io.vlingo.xoom.codegen.content.CodeElementFormatter.simpleNameToAttribute;
import static java.util.stream.Collectors.toSet;

public class EntityUnitTestTemplateData extends TemplateData {

  private final TemplateParameters parameters;

  public static List<TemplateData> from(final String basePackage,
                                        final StorageType storageType,
                                        final ProjectionType projectionType,
                                        final List<CodeGenerationParameter> aggregates,
                                        final List<CodeGenerationParameter> valueObjects,
                                        final List<Content> contents) {
    return aggregates.stream().map(aggregate -> new EntityUnitTestTemplateData(basePackage, storageType, projectionType, aggregate, valueObjects, contents))
            .collect(Collectors.toList());
  }

  private EntityUnitTestTemplateData(final String basePackage,
                                     final StorageType storageType,
                                     final ProjectionType projectionType,
                                     final CodeGenerationParameter aggregate,
                                     final List<CodeGenerationParameter> valueObjects,
                                     final List<Content> contents) {
    final String entityName =
            JavaTemplateStandard.AGGREGATE.resolveClassname(aggregate.value);

    final String stateName =
            JavaTemplateStandard.AGGREGATE_STATE.resolveClassname(aggregate.value);

    final String packageName =
            ContentQuery.findPackage(JavaTemplateStandard.AGGREGATE_PROTOCOL, aggregate.value, contents);

    final TestDataValueGenerator.TestDataValues initialTestDataValues =
            TestDataValueGenerator.with(aggregate, valueObjects).generate();

    final Optional<String> defaultFactoryMethod =
            resolveDefaultFactoryMethodName(aggregate);

    final AuxiliaryEntityCreation auxiliaryEntityCreation =
            AuxiliaryEntityCreation.from(aggregate, valueObjects, defaultFactoryMethod, initialTestDataValues);

    final List<TestCase> testCases =
            TestCase.from(aggregate, valueObjects, defaultFactoryMethod, initialTestDataValues, projectionType);

    this.parameters =
            TemplateParameters.with(TemplateParameter.PACKAGE_NAME, packageName)
                    .and(TemplateParameter.DISPATCHER_NAME, JavaTemplateStandard.MOCK_DISPATCHER.resolveClassname())
                    .and(TemplateParameter.AUXILIARY_ENTITY_CREATION, auxiliaryEntityCreation)
                    .and(TemplateParameter.ENTITY_CREATION_METHOD, AuxiliaryEntityCreation.METHOD_NAME)
                    .and(TemplateParameter.AGGREGATE_PROTOCOL_VARIABLE, simpleNameToAttribute(aggregate.value))
                    .and(TemplateParameter.ENTITY_UNIT_TEST_NAME, JavaTemplateStandard.ENTITY_UNIT_TEST.resolveClassname(entityName))
                    .and(TemplateParameter.AGGREGATE_PROTOCOL_NAME, aggregate.value).and(TemplateParameter.ENTITY_NAME, entityName)
                    .and(TemplateParameter.STATE_NAME, stateName).and(TemplateParameter.ADAPTER_NAME, JavaTemplateStandard.ADAPTER.resolveClassname(stateName))
                    .and(TemplateParameter.SOURCED_EVENTS, SourcedEvent.from(storageType, aggregate))
                    .and(TemplateParameter.STORAGE_TYPE, storageType).and(TemplateParameter.TEST_CASES, testCases)
                    .and(TemplateParameter.PRODUCTION_CODE, false).and(TemplateParameter.UNIT_TEST, true)
                    .addImport(resolveBaseEntryImport(projectionType))
                    .addImport(resolveMockDispatcherImport(basePackage))
                    .addImports(resolveMethodParameterImports(aggregate))
                    .addImport(resolveValueObjectImports(aggregate, contents))
                    .addImports(resolveAdapterImports(basePackage, storageType, aggregate));
  }

  private Set<String> resolveMethodParameterImports(final CodeGenerationParameter aggregate) {
    return aggregate.retrieveAllRelated(Label.AGGREGATE_METHOD)
            .map(method -> AggregateDetail.findInvolvedStateFields(aggregate, method.value))
            .map(involvedFields -> AggregateDetail.resolveImports(involvedFields))
            .flatMap(Set::stream).collect(Collectors.toSet());
  }

  private String resolveBaseEntryImport(final ProjectionType projectionType) {
    if (projectionType.isOperationBased()) {
      return "";
    }
    return BaseEntry.class.getCanonicalName();
  }

  private String resolveMockDispatcherImport(final String basePackage) {
    final String mockDispatcherName = JavaTemplateStandard.MOCK_DISPATCHER.resolveClassname();
    final String mockDispatcherPackage = MockDispatcherDetail.resolvePackage(basePackage);
    return qualifiedNameOf(mockDispatcherPackage, mockDispatcherName);
  }

  private String resolveValueObjectImports(final CodeGenerationParameter aggregate, final List<Content> contents) {
    if (!ValueObjectDetail.useValueObject(aggregate)) {
      return "";
    }
    return CodeElementFormatter.importAllFrom(ContentQuery.findPackage(JavaTemplateStandard.VALUE_OBJECT, contents));
  }

  private Set<String> resolveAdapterImports(final String basePackage,
                                            final StorageType storageType,
                                            final CodeGenerationParameter aggregate) {
    final String persistencePackage =
            PersistenceDetail.resolvePackage(basePackage);

    switch (storageType) {
      case STATE_STORE:
        final String stateName = JavaTemplateStandard.AGGREGATE_STATE.resolveClassname(aggregate.value);
        final String adapterName = JavaTemplateStandard.ADAPTER.resolveClassname(stateName);
        return Stream.of(qualifiedNameOf(persistencePackage, adapterName)).collect(toSet());
      case JOURNAL:
        return aggregate.retrieveAllRelated(Label.DOMAIN_EVENT)
                .map(event -> JavaTemplateStandard.ADAPTER.resolveClassname(event.value))
                .map(adapter -> qualifiedNameOf(persistencePackage, adapter))
                .collect(toSet());
      default:
        return Collections.emptySet();
    }
  }

  private Optional<String> resolveDefaultFactoryMethodName(final CodeGenerationParameter aggregate) {
    return aggregate.retrieveAllRelated(Label.AGGREGATE_METHOD)
            .filter(method -> method.retrieveRelatedValue(Label.FACTORY_METHOD, Boolean::valueOf))
            .map(method -> method.value).findFirst();
  }

  @Override
  public TemplateParameters parameters() {
    return parameters;
  }

  @Override
  public TemplateStandard standard() {
    return JavaTemplateStandard.ENTITY_UNIT_TEST;
  }

  @Override
  public String filename() {
    return parameters.find(TemplateParameter.ENTITY_UNIT_TEST_NAME);
  }
}