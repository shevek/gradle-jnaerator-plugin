package org.anarres.gradle.plugin.jnaerator;

import java.io.File;
import java.util.concurrent.Callable;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.ConventionMapping;

/**
 *
 * @author shevek
 */
public class JNAeratorPlugin implements Plugin<Project> {

    @Override
    public void apply(final Project project) {
        final JNAeratorPluginExtension extension = project.getExtensions().create(JNAeratorPluginExtension.NAME, JNAeratorPluginExtension.class);
        extension.setOutputDir(new File(project.getBuildDir(), "generated-sources/jnaerator"));
        project.getTasks().create("jnaeratorGenerate", JNAeratorTask.class, new Action<JNAeratorTask>() {
            @Override
            public void execute(JNAeratorTask t) {
                ConventionMapping cm = t.getConventionMapping();
                cm.map("outputDir", new Callable<File>() {
                    @Override
                    public File call() throws Exception {
                        return extension.getOutputDir();
                    }
                });
                cm.map("outputPackage", new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return extension.getOutputPackage();
                    }
                });
                cm.map("libraryName", new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return extension.getLibraryName();
                    }
                });
                cm.map("headerFiles", new Callable<FileCollection>() {
                    @Override
                    public FileCollection call() throws Exception {
                        return extension.getHeaderFiles();
                    }
                });
            }
        });
    }
}
