package org.anarres.gradle.plugin.jnaerator;

import com.ochafik.lang.jnaerator.JNAerator;
import com.ochafik.lang.jnaerator.JNAeratorCommandLineArgs;
import com.ochafik.lang.jnaerator.JNAeratorConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author shevek
 */
public class JNAeratorTask extends DefaultTask {

    private static final Logger LOG = LoggerFactory.getLogger(JNAeratorTask.class);

    private File outputDir;
    private String libraryName;
    private String packageName;
    private FileCollection headerFiles;

    @OutputDirectory
    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(Object outputDir) {
        this.outputDir = getProject().file(outputDir);
    }

    public void outputDir(Object outputDir) {
        setOutputDir(outputDir);
    }

    @Input
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        LOG.info("setLibraryName: " + libraryName);
        this.libraryName = libraryName;
    }

    public void libraryName(String libraryName) {
        setLibraryName(libraryName);
    }

    @Input
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void packageName(String packageName) {
        setPackageName(packageName);
    }

    @InputFiles
    public FileCollection getHeaderFiles() {
        LOG.info("getHeaderFiles = " + headerFiles);
        return headerFiles;
    }

    public void setHeaderFiles(Object... headerFiles) {
        LOG.info("setHeaderFiles = " + headerFiles);
        this.headerFiles = getProject().files(headerFiles);
    }

    public void headerFiles(Object... headerFiles) {
        setHeaderFiles(headerFiles);
    }

    @TaskAction
    public void run() {
        List<String> args = new ArrayList<String>();

        args.add(JNAeratorCommandLineArgs.OptionDef.CurrentLibrary.clSwitch);
        args.add(getLibraryName());
        args.add(JNAeratorCommandLineArgs.OptionDef.CurrentPackage.clSwitch);
        args.add(getPackageName());

        for (File file : getHeaderFiles())
            args.add(file.getAbsolutePath());

        args.add(JNAeratorCommandLineArgs.OptionDef.OutputMode.clSwitch);
        args.add(JNAeratorConfig.OutputMode.Directory.name());
        args.add(JNAeratorCommandLineArgs.OptionDef.Runtime.clSwitch);
        args.add(JNAeratorConfig.Runtime.JNA.name());

        String outputPath = getPackageName().replace('.', File.separatorChar);
        File outputDir = new File(getOutputDir(), outputPath);
        args.add(outputDir.getAbsolutePath());

        args.add(JNAeratorCommandLineArgs.OptionDef.Verbose.clSwitch);

        DefaultGroovyMethods.deleteDir(outputDir);
        outputDir.mkdirs();
        JNAerator.main(args.toArray(new String[args.size()]));
    }
}
