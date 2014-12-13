package org.anarres.gradle.plugin.jnaerator;

import com.ochafik.lang.jnaerator.JNAerator;
import com.ochafik.lang.jnaerator.JNAeratorCommandLineArgs;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

/**
 *
 * @author shevek
 */
public class JNAeratorTask extends SourceTask {

    private FileResolver fileResolver;

    @OutputDirectory
    private File outputDir;

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    @TaskAction
    public void run() {
        DefaultGroovyMethods.deleteDir(outputDir);
        outputDir.mkdirs();

        List<String> args = new ArrayList<String>();
        String sw = JNAeratorCommandLineArgs.OptionDef.OutputDir.clSwitch;

        JNAerator.main(args.toArray(new String[args.size()]));
    }
}
