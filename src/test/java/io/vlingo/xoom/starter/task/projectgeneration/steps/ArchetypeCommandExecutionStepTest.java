package io.vlingo.xoom.starter.task.projectgeneration.steps;

import io.vlingo.xoom.codegen.parameter.CodeGenerationParameters;
import io.vlingo.xoom.starter.infrastructure.HomeDirectory;
import io.vlingo.xoom.starter.infrastructure.Infrastructure;
import io.vlingo.xoom.starter.infrastructure.terminal.CommandRetainer;
import io.vlingo.xoom.starter.infrastructure.terminal.Terminal;
import io.vlingo.xoom.starter.task.TaskExecutionContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static io.vlingo.xoom.codegen.parameter.Label.*;
import static io.vlingo.xoom.starter.infrastructure.terminal.Terminal.*;

public class ArchetypeCommandExecutionStepTest {

    private TaskExecutionContext context;

    private static final String WINDOWS_ROOT_FOLDER = Paths.get("D:", "tools", "starter").toString();

    private static final String DEFAULT_ROOT_FOLDER = Paths.get("home", "tools", "starter").toString();

    private static final String WINDOWS_ARCHETYPE_PATH =
            Paths.get(WINDOWS_ROOT_FOLDER, "resources", "archetypes").toString();

    private static final String DEFAULT_ARCHETYPE_PATH =
            Paths.get(DEFAULT_ROOT_FOLDER, "resources", "archetypes").toString();

    private static final String EXPECTED_ARCHETYPE_COMMAND_ON_WINDOWS =
                    "D: && cd " + WINDOWS_ARCHETYPE_PATH + " && mvnw.cmd -f kubernetes-archetype" + File.separator +
                    "pom.xml clean install " + "&& mvnw.cmd archetype:generate -B -DarchetypeCatalog=internal " +
                    "-DarchetypeGroupId=io.vlingo " +
                    "-DarchetypeArtifactId=vlingo-xoom-kubernetes-archetype " +
                    "-DarchetypeVersion=1.0 -Dversion=1.0 -DgroupId=io.vlingo " +
                    "-DartifactId=starter-example -DmainClass=io.vlingo.starterexample.infrastructure.Bootstrap " +
                    "-Dpackage=io.vlingo.starterexample -DvlingoXoomVersion=1.2.9 " +
                    "-DdockerImage=starter-example-image -Dk8sPodName=starter-example-pod " +
                    "-Dk8sImage=starter-example-image ";

    private static final String EXPECTED_ARCHETYPE_COMMAND =
                    "cd " + DEFAULT_ARCHETYPE_PATH +  " && ./mvnw -f ." + File.separator +  "kubernetes-archetype"
                    + File.separator + "pom.xml clean install && ./mvnw archetype:generate -B " +
                    "-DarchetypeCatalog=internal -DarchetypeGroupId=io.vlingo " +
                    "-DarchetypeArtifactId=vlingo-xoom-kubernetes-archetype " +
                    "-DarchetypeVersion=1.0 -Dversion=1.0 -DgroupId=io.vlingo " +
                    "-DartifactId=starter-example -DmainClass=io.vlingo.starterexample.infrastructure.Bootstrap " +
                    "-Dpackage=io.vlingo.starterexample -DvlingoXoomVersion=1.2.9 " +
                    "-DdockerImage=starter-example-image -Dk8sPodName=starter-example-pod " +
                    "-Dk8sImage=starter-example-image ";

    @Test
    public void testCommandPreparationWithKubernetesArchetypeOnWindows() {
        Terminal.enable(WINDOWS);
        Infrastructure.resolvePrimaryResources(HomeDirectory.from(WINDOWS_ROOT_FOLDER));
        context.with(loadGenerationParameters("E:\\projects"));
        final CommandRetainer commandRetainer = new CommandRetainer();
        new ArchetypeCommandExecutionStep(commandRetainer).process(context);
        final String[] commandSequence = commandRetainer.retainedCommandsSequence().get(0);
        Assertions.assertEquals(Terminal.supported().initializationCommand(), commandSequence[0]);
        Assertions.assertEquals(Terminal.supported().parameter(), commandSequence[1]);
        Assertions.assertEquals(EXPECTED_ARCHETYPE_COMMAND_ON_WINDOWS, commandSequence[2]);
    }

    @Test
    public void testCommandPreparationWithKubernetesArchetypeOnLinux() {
        Terminal.enable(LINUX);
        Infrastructure.resolvePrimaryResources(HomeDirectory.from(DEFAULT_ROOT_FOLDER));
        context.with(loadGenerationParameters("/home/projects"));
        final CommandRetainer commandRetainer = new CommandRetainer();
        new ArchetypeCommandExecutionStep(commandRetainer).process(context);
        final String[] commandSequence = commandRetainer.retainedCommandsSequence().get(0);
        Assertions.assertEquals(Terminal.supported().initializationCommand(), commandSequence[0]);
        Assertions.assertEquals(Terminal.supported().parameter(), commandSequence[1]);
        Assertions.assertEquals(EXPECTED_ARCHETYPE_COMMAND, commandSequence[2]);
    }

    @Test
    public void testCommandPreparationWithKubernetesArchetypeOnMac() {
        Terminal.enable(MAC_OS);
        Infrastructure.resolvePrimaryResources(HomeDirectory.from(DEFAULT_ROOT_FOLDER));
        context.with(loadGenerationParameters("/home/projects"));
        final CommandRetainer commandRetainer = new CommandRetainer();
        new ArchetypeCommandExecutionStep(commandRetainer).process(context);
        final String[] commandSequence = commandRetainer.retainedCommandsSequence().get(0);
        Assertions.assertEquals(Terminal.supported().initializationCommand(), commandSequence[0]);
        Assertions.assertEquals(Terminal.supported().parameter(), commandSequence[1]);
        Assertions.assertEquals(EXPECTED_ARCHETYPE_COMMAND, commandSequence[2]);
    }

    private CodeGenerationParameters loadGenerationParameters(final String targetFolder) {
        return CodeGenerationParameters.from(VERSION, "1.0")
                .add(GROUP_ID, "io.vlingo").add(ARTIFACT_ID, "starter-example")
                .add(PACKAGE, "io.vlingo.starterexample").add(XOOM_VERSION, "1.2.9")
                .add(TARGET_FOLDER, targetFolder).add(DOCKER_IMAGE, "starter-example-image")
                .add(KUBERNETES_IMAGE, "starter-example-image").add(KUBERNETES_POD_NAME, "starter-example-pod")
                .add(MAIN_CLASS, "io.vlingo.starterexample.infrastructure.Bootstrap");
    }

    @BeforeEach
    public void setUp() {
        Infrastructure.clear();
        this.context = TaskExecutionContext.withoutOptions();
    }

    @AfterEach
    public void clear() {
        Terminal.disable();
    }

}
