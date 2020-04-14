// Copyright © 2012-2020 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.xoom.starter.template.archetype;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;

public enum ArchetypeProperties {

    VERSION("version"),
    GROUP_ID("group.id"),
    ARTIFACT_ID("artifact.id"),
    PACKAGE("package"),
    TARGET_FOLDER("target.folder", false),
    XOOM_SERVER_VERSION("vlingo.xoom.server.version"),
    DEPLOYMENT("deployment", false),
    KUBERNETES_IMAGE("k8s.image"),
    KUBERNETES_POD_NAME("k8s.pod.name");

    private final String key;
    private final boolean supportedByMaven;

    ArchetypeProperties(final String key) {
        this(key, true);
    }

    ArchetypeProperties(final String key, final boolean supportedByMaven) {
        this.key = key;
        this.supportedByMaven = supportedByMaven;
    }

    public String literal() {
        return key;
    }

    public boolean isSupportedByMaven() {
        return supportedByMaven;
    }

    public String toMavenFormat() {
        return LOWER_UNDERSCORE.to(LOWER_CAMEL, key.replaceAll("\\.", "_"));
    }

}