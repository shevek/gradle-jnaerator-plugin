package org.anarres.gradle.plugin.jnaerator;

import java.util.Collections;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shevek
 */
public class JNAeratorPluginApplyTest {

    Project project;

    @Before
    public void setUp() {
        project = ProjectBuilder.builder().build();
    }

    @Test
    public void testApply() {
        project.apply(Collections.singletonMap("plugin", "java"));
        // project.apply(Collections.singletonMap("plugin", "org.anarres.jnaerator"));
        project.getPlugins().apply(JNAeratorPlugin.class);
        assertTrue("Project is missing plugin", project.getPlugins().hasPlugin(JNAeratorPlugin.class));
        Task task = project.getTasks().findByName("jnaerator");
        assertNotNull("Project is missing jnaerator task", task);
        assertTrue("JNAerator task is the wrong type", task instanceof DefaultTask);
        assertTrue("JNAerator task should be enabled", ((DefaultTask) task).isEnabled());
    }
}
