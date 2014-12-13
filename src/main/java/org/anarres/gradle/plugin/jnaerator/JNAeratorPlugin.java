package org.anarres.gradle.plugin.jnaerator;

import java.io.File;
import java.util.concurrent.Callable;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 *
 * @author shevek
 */
public class JNAeratorPlugin implements Plugin<Project> {

    @Override
    public void apply(final Project project) {
        final JNAeratorPluginExtension extension = project.getExtensions().create(JNAeratorPluginExtension.NAME, JNAeratorPluginExtension.class);
        project.getTasks().create("jnaeratorGenerate", JNAeratorTask.class, new Action<JNAeratorTask>() {
            @Override
            public void execute(JNAeratorTask t) {
                t.getConventionMapping().map("outputDir", new Callable<File>() {
                    @Override
                    public File call() throws Exception {
                        return new File(project.getBuildDir(), "generated-sources/jnaerator");
                    }
                });
            }
        });
    }

}
