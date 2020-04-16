package io.vlingo.xoom.starter.task.docker.steps;

import io.vlingo.xoom.starter.task.TaskExecutionContext;
import io.vlingo.xoom.starter.task.docker.DockerCommandException;
import io.vlingo.xoom.starter.task.option.OptionName;
import io.vlingo.xoom.starter.task.option.OptionValue;
import io.vlingo.xoom.starter.task.template.Terminal;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

import static io.vlingo.xoom.starter.task.Property.DOCKER_IMAGE;

public class DockerPackageCommandResolverStepTest {

    private static final String EXPECTED_COMMAND = "cd /home/projects/xoom-app && mvn clean package && docker build ./ -t xoom-app:latest";

    @Test
    public void testDockerPackageCommandResolution() {
        final OptionValue tag =
                OptionValue.with(OptionName.TAG, "latest");

        final OptionValue directory =
                OptionValue.with(OptionName.CURRENT_DIRECTORY, "/home/projects/xoom-app");

        final Properties properties = new Properties();
        properties.put(DOCKER_IMAGE.literal(), "xoom-app");

        final TaskExecutionContext context =
                TaskExecutionContext.withOptions(Arrays.asList(tag, directory));

        context.onProperties(properties);

        new DockerPackageCommandResolverStep().process(context);

        Assert.assertEquals(Terminal.active().initializationCommand(), context.commands()[0]);
        Assert.assertEquals(Terminal.active().parameter(), context.commands()[1]);
        Assert.assertEquals(EXPECTED_COMMAND, context.commands()[2]);
    }

    @Test(expected = DockerCommandException.class)
    public void testDockerPackageCommandResolutionWithoutImage() {
        final OptionValue tag =
                OptionValue.with(OptionName.TAG, "latest");

        final OptionValue directory =
                OptionValue.with(OptionName.CURRENT_DIRECTORY, "/home/projects/xoom-app");

        final TaskExecutionContext context =
                TaskExecutionContext.withOptions(Arrays.asList(tag, directory));

        context.onProperties(new Properties());

        new DockerPackageCommandResolverStep().process(context);
    }

}
