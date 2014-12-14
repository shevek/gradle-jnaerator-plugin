package org.anarres.gradle.plugin.jnaerator;

import com.ochafik.lang.jnaerator.JNAerator;
import com.ochafik.lang.jnaerator.JNAeratorCommandLineArgs;
import com.ochafik.lang.jnaerator.JNAeratorConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

/**
 *
 * @author shevek
 */
public class JNAeratorTask extends SourceTask {

    @OutputDirectory
    private File outputDir;
    @Input
    private String outputPackage;
    @Input
    private String libraryName;
    @InputFiles
    private FileCollection headerFiles;

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    public String getOutputPackage() {
        return outputPackage;
    }

    public void setOutputPackage(String outputPackage) {
        this.outputPackage = outputPackage;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public FileCollection getHeaderFiles() {
        return headerFiles;
    }

    public void setHeaderFiles(FileCollection headerFiles) {
        this.headerFiles = headerFiles;
    }

    @TaskAction
    public void run() {
        List<String> args = new ArrayList<String>();

        args.add(JNAeratorCommandLineArgs.OptionDef.CurrentLibrary.clSwitch);
        args.add(getLibraryName());
        for (File file : getHeaderFiles())
            args.add(file.getAbsolutePath());
        args.add(JNAeratorCommandLineArgs.OptionDef.OutputMode.clSwitch);
        args.add(JNAeratorConfig.OutputMode.Directory.name());
        args.add(JNAeratorCommandLineArgs.OptionDef.Runtime.clSwitch);
        args.add(JNAeratorConfig.Runtime.JNA.name());

        String outputPath = getOutputPackage().replace('.', File.separatorChar);
        File outputDir = new File(getOutputDir(), outputPath);
        args.add(outputDir.getAbsolutePath());

        args.add(JNAeratorCommandLineArgs.OptionDef.Verbose.clSwitch);

        DefaultGroovyMethods.deleteDir(outputDir);
        outputDir.mkdirs();
        JNAerator.main(args.toArray(new String[args.size()]));
    }
}
