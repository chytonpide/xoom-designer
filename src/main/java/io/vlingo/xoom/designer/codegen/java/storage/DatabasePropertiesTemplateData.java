// Copyright © 2012-2022 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.designer.codegen.java.storage;

import io.vlingo.xoom.codegen.template.TemplateData;
import io.vlingo.xoom.codegen.template.TemplateParameters;
import io.vlingo.xoom.codegen.template.TemplateStandard;
import io.vlingo.xoom.designer.codegen.java.JavaTemplateStandard;
import io.vlingo.xoom.designer.codegen.java.TemplateParameter;

import java.util.Map;

public class DatabasePropertiesTemplateData extends TemplateData {

  private final TemplateParameters templateParameters;

  public DatabasePropertiesTemplateData(final String appName,
                                        final Map<Model, DatabaseType> databases) {
    this.templateParameters = loadParameters(appName, databases);
  }

  private TemplateParameters loadParameters(final String appName,
                                            final Map<Model, DatabaseType> databases) {
    final TemplateParameters parameters =
            TemplateParameters.with(TemplateParameter.RESOURCE_FILE, true).and(TemplateParameter.PRODUCTION_CODE, false);

    databases.entrySet().forEach(entry -> {
      final TemplateParameter parameter =
              entry.getKey().isQueryModel() ?
                      TemplateParameter.QUERY_DATABASE_PARAMETER :
                      TemplateParameter.DEFAULT_DATABASE_PARAMETER;

      parameters.and(parameter, new Database(appName, entry.getKey(), entry.getValue()));
    });

    return parameters;
  }

  @Override
  public TemplateParameters parameters() {
    return templateParameters;
  }

  @Override
  public TemplateStandard standard() {
    return JavaTemplateStandard.DATABASE_PROPERTIES;
  }
}
