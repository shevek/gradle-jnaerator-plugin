package org.anarres.gradle.plugin.jnaerator;

import java.io.File;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

/**
 *
 * @author shevek
 */
public class JNAeratorPlugin implements Plugin<Project> {

    @Override
    public void apply(final Project project) {
        project.getPlugins().apply(JavaPlugin.class);

        // final JNAeratorPluginExtension extension = project.getExtensions().create(JNAeratorPluginExtension.NAME, JNAeratorPluginExtension.class);
        // extension.setOutputDir(new File(project.getBuildDir(), "generated-sources/jnaerator"));

        JNAeratorTask task = project.getTasks().create("jnaerator", JNAeratorTask.class);
        task.setOutputDir(new File(project.getBuildDir(), "generated-sources/jnaerator"));

        project.getTasks().getByName("compileJava").dependsOn(task);
        SourceSetContainer sourceSets = project.getConvention().getPlugin(JavaPluginConvention.class).getSourceSets();
        final SourceSet mainSourceSet = sourceSets.getByName("main");
        mainSourceSet.getJava().srcDir(task.getOutputDir());
    }
}
