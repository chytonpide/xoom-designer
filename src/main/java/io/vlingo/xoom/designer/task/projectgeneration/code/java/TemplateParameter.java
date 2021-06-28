// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.task.projectgeneration.code.java;

import io.vlingo.xoom.codegen.template.ParameterKey;

import static io.vlingo.xoom.codegen.template.TemplateParameters.PRODUCTION_CODE_KEY;

public enum TemplateParameter implements ParameterKey {

  BASE_PACKAGE("basePackage"),
  PACKAGE_NAME("packageName"),
  APPLICATION_NAME("appName"),
  COLLECTION_MUTATIONS("collectionMutations"),
  AUTO_DISPATCH_MAPPING_NAME("autoDispatchMappingName"),
  AUTO_DISPATCH_HANDLERS_MAPPING_NAME("autoDispatchHandlersMappingName"),
  AGGREGATES("aggregates"),
  AGGREGATE_PROTOCOL_NAME("aggregateProtocolName"),
  AGGREGATE_PROTOCOL_VARIABLE("aggregateProtocolVariable"),
  BLOCKING_MESSAGING("blockingMessaging"),
  DOMAIN_EVENT_NAME("domainEventName"),
  HANDLER_INDEXES("handlerIndexes"),
  HANDLER_ENTRIES("handlerEntries"),
  ENTITY_NAME("entityName"),
  ENTITY_CREATION_METHOD("entityCreationMethodName"),
  DATA_OBJECTS("dataObjects"),
  STATE_DATA_OBJECT_NAME("dataName"),
  DATA_OBJECT_QUALIFIED_NAME("dataQualifiedName"),
  IMPORTS("imports"),
  CUSTOM_INITIALIZATION("customInitialization"),
  ADAPTERS("adapters"),
  STORE_PROVIDER_NAME("storeProviderName"),
  STATE_NAME("stateName"),
  DATA_OBJECT_NAME("dataObjectName"),
  VALUE_OBJECT_NAME("valueObjectName"),
  DATA_VALUE_OBJECT_NAME("dataValueObjectName"),
  INDEX_NAME("indexName"),
  PROJECTION_SOURCES("sources"),
  PROJECTION_SOURCE_TYPES_NAME("projectionSourceTypesName"),
  PROJECTION_SOURCE_TYPES_QUALIFIED_NAME("projectionSourceTypesQualifiedName"),
  FACTORY_METHOD("factoryMethod"),
  SOURCE_NAME("sourceName"),
  ADAPTER_NAME("adapterName"),
  STATE_QUALIFIED_CLASS_NAME("stateQualifiedClassName"),
  STORAGE_TYPE("storageType"),
  EVENT_SOURCED("eventSourced"),
  REQUIRE_ADAPTERS("requireAdapters"),
  RESOURCE_FILE("resourceFile"),
  SCHEMATA_FILE("schemataFile"),
  PRODUCTION_CODE(PRODUCTION_CODE_KEY),
  PROJECTIONS("projections"),
  PROJECTION_NAME("projectionName"),
  PROJECTION_TYPE("projectionType"),
  ID_NAME("idName"),
  ID_TYPE("idType"),
  EVENT_HANDLERS("eventHandlers"),
  SOURCED_EVENTS("sourcedEvents"),
  OPERATION_BASED("operationBased"),
  ROUTE_DECLARATIONS("routeDeclarations"),
  ROUTE_SIGNATURE("routeSignature"),
  ROUTE_METHOD("routeMethod"),
  ROUTE_PATH("routePath"),
  ROUTE_METHODS("routeMethods"),
  ROUTE_MAPPING_VALUE("routeMappingValue"),
  ROUTE_HANDLER_INVOCATION("routeHandlerInvocation"),
  REQUIRE_ENTITY_LOADING("requireEntityLoading"),
  ADAPTER_HANDLER_INVOCATION("adapterHandlerInvocation"),
  HANDLERS_CONFIG_NAME("handlersConfigName"),
  QUERIES("queries"),
  QUERIES_NAME("queriesName"),
  QUERIES_ACTOR_NAME("queriesActorName"),
  QUERY_BY_ID_METHOD_NAME("queryByIdMethodName"),
  QUERY_ALL_METHOD_NAME("queryAllMethodName"),
  QUERY_ALL_INDEX_NAME("queryAllIndexName"),
  QUERY_BY_ID_INDEX_NAME("queryByIdIndexName"),
  PROJECTION_TO_DESCRIPTION("projectToDescriptions"),
  PERSISTENT_TYPES("persistentTypes"),
  USE_CQRS("useCQRS"),
  USE_PROJECTIONS("useProjections"),
  USE_ANNOTATIONS("useAnnotations"),
  USE_AUTO_DISPATCH("useAutoDispatch"),
  MODEL("model"),
  URI_ROOT("uriRoot"),
  MEMBERS("members"),
  MEMBER_NAMES("memberNames"),
  MEMBERS_ASSIGNMENT("membersAssignment"),
  VALUE_OBJECT_INITIALIZERS("valueObjectInitializers"),
  VALUE_OBJECT_TRANSLATIONS("valueObjectTranslations"),
  METHODS("methods"),
  METHOD_NAME("methodName"),
  METHOD_SCOPE("methodScope"),
  METHOD_PARAMETERS("methodParameters"),
  METHOD_INVOCATION_PARAMETERS("methodInvocationParameters"),
  MODEL_ACTOR("modelActor"),
  MODEL_PROTOCOL("modelProtocol"),
  MODEL_ATTRIBUTE("modelAttribute"),
  CONSTRUCTOR_PARAMETERS("constructorParameters"),
  CONSTRUCTOR_INVOCATION_PARAMETERS("constructorInvocationParameters"),
  RETRIEVAL_ROUTE("retrievalRoute"),
  REST_RESOURCE_NAME("resourceName"),
  REST_RESOURCE_PACKAGE("restResourcePackage"),
  DEFAULT_DATABASE_PARAMETER("databaseParameter"),
  QUERY_DATABASE_PARAMETER("queryDatabaseParameter"),
  REST_RESOURCES("restResources"),
  TYPE_REGISTRIES("registries"),
  XOOM_INITIALIZER_CLASS("xoomInitializerClass"),
  STAGE_INSTANTIATION_VARIABLES("stageInstantiationVariables"),
  PROJECTION_DISPATCHER_PROVIDER_NAME("projectionDispatcherProviderName"),
  PROVIDERS("providers"),
  LOCAL_TYPE_NAME("localTypeName"),
  EXCHANGE_MAPPER_NAME("exchangeMapperName"),
  EXCHANGE_ADAPTER_NAME("exchangeAdapterName"),
  EXCHANGE_RECEIVER_HOLDER_NAME("exchangeReceiverHolderName"),
  EXCHANGE_ROLE("exchangeRole"),
  EXCHANGE_RECEIVERS("exchangeReceivers"),
  INLINE_EXCHANGE_NAMES("inlineExchangeNames"),
  EXCHANGE_NAMES("exchangeNames"),
  SCHEMA_GROUP_NAME("schemaGroupName"),
  EXCHANGES("exchanges"),
  PRODUCER_EXCHANGES("producerExchanges"),
  EXCHANGE_BOOTSTRAP_NAME("exchangeBootstrapName"),
  HAS_EXCHANGE("hasExchange"),
  HAS_PRODUCER_EXCHANGE("hasProducerExchange"),
  HAS_CONSUMER_EXCHANGE("hasConsumerExchange"),
  DEFAULT_SCHEMA_VERSION("defaultSchemaVersion"),
  SCHEMATA_SPECIFICATION_NAME("schemataSpecificationName"),
  SCHEMA_CATEGORY("schemaCategory"),
  FIELD_DECLARATIONS("fieldsDeclarations"),
  DESIGNER_MODEL("projectGenerationSettings"),
  DESIGNER_MODEL_JSON("designerModelJson"),
  POM_SECTION("pomSection"),
  OFFSET("offset"),
  README_FILE("readmeFile"),
  PRODUCER_ORGANIZATION("producerOrganization"),
  PRODUCER_UNIT("producerUnit"),
  PRODUCER_SCHEMAS("producerSchemas"),
  CONSUMER_SCHEMAS("consumerSchemas"),
  STATIC_FACTORY_METHODS("staticFactoryMethods"),
  REST_RESOURCE_UNIT_TEST_NAME("resourceUnitTestName"),
  QUERIES_UNIT_TEST_NAME("queriesUnitTestName"),
  PROJECTION_UNIT_TEST_NAME("projectionUnitTestName"),
  TEST_CASES("testCases"),
  UNIT_TEST("unitTest"),
  ENTITY_UNIT_TEST_NAME("entityUnitTestName"),
  DISPATCHER_NAME("dispatcherName"),
  AUXILIARY_ENTITY_CREATION("auxiliaryEntityCreation"),
  STATE_FIELDS("stateFields"),
  VALUE_OBJECT_FIELDS("valueObjectFields"),
  CLUSTER_SETTINGS("clusterSettings"),
  TURBO_SETTINGS("turboSettings"),
  DATA_OBJECT_PARAMS("dataObjectParams"),
  UI_TYPE("uiType");

  public final String key;

  TemplateParameter(String key) {
    this.key = key;
  }

  @Override
  public String value() {
    return key;
  }

}
