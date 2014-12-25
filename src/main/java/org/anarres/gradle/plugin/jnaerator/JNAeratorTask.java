package org.anarres.gradle.plugin.jnaerator;

import com.ochafik.lang.jnaerator.JNAerator;
import com.ochafik.lang.jnaerator.JNAeratorCommandLineArgs;
import com.ochafik.lang.jnaerator.JNAeratorConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

/**
 *
 * @author shevek
 */
public class JNAeratorTask extends DefaultTask {

    // private static final Logger LOG = LoggerFactory.getLogger(JNAeratorTask.class);
    @OutputDirectory
    private File outputDir;
    @Input
    private String libraryName;
    @Input
    private String packageName;
    @InputFiles
    private FileCollection headerFiles;
    @Input
    private JNAeratorConfig.Runtime runtimeMode = JNAeratorConfig.Runtime.JNA;
    @Input
    private final Set<String> define = new HashSet<String>();
    @Input
    private final Set<String> undefine = new HashSet<String>();
    @Input
    private List<String> extraArgs = new ArrayList<String>();

    @OutputDirectory
    public File getOutputDir() {
        getLogger();
        return outputDir;
    }

    public void setOutputDir(@Nonnull Object outputDir) {
        this.outputDir = getProject().file(outputDir);
    }

    public void outputDir(@Nonnull Object outputDir) {
        setOutputDir(outputDir);
    }

    @Input
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(@Nonnull String libraryName) {
        // LOG.info("setLibraryName: " + libraryName);
        this.libraryName = libraryName;
    }

    public void libraryName(@Nonnull String libraryName) {
        setLibraryName(libraryName);
    }

    @Input
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(@Nonnull String packageName) {
        this.packageName = packageName;
    }

    public void packageName(@Nonnull String packageName) {
        setPackageName(packageName);
    }

    @InputFiles
    public FileCollection getHeaderFiles() {
        // LOG.info("getHeaderFiles = " + headerFiles);
        return headerFiles;
    }

    public void setHeaderFiles(@Nonnull Object... headerFiles) {
        // LOG.info("setHeaderFiles = " + headerFiles);
        this.headerFiles = getProject().files(headerFiles);
    }

    public void headerFiles(@Nonnull Object... headerFiles) {
        setHeaderFiles(headerFiles);
    }

    @Nonnull
    public JNAeratorConfig.Runtime getRuntimeMode() {
        return runtimeMode;
    }

    public void setRuntimeMode(@Nonnull JNAeratorConfig.Runtime runtimeMode) {
        this.runtimeMode = runtimeMode;
    }

    public void runtimeMode(@Nonnull Object runtimeMode) {
        if (runtimeMode instanceof JNAeratorConfig.Runtime)
            this.runtimeMode = (JNAeratorConfig.Runtime) runtimeMode;
        else
            this.runtimeMode = JNAeratorConfig.Runtime.valueOf(String.valueOf(runtimeMode));
    }

    public void define(@Nonnull Object... args) {
        for (Object arg : args)
            define.add(String.valueOf(arg));
    }

    public void undefine(@Nonnull Object... args) {
        for (Object arg : args)
            undefine.add(String.valueOf(arg));
    }

    @Nonnull
    public List<String> getExtraArgs() {
        return extraArgs;
    }

    public void setExtraArgs(@Nonnull List<String> extraArgs) {
        this.extraArgs = extraArgs;
    }

    public void extraArgs(@Nonnull Object... extraArgs) {
        for (Object arg : extraArgs)
            this.extraArgs.add(String.valueOf(arg));
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
        args.add(getRuntimeMode().name());

        args.add(JNAeratorCommandLineArgs.OptionDef.OutputDir.clSwitch);
        // String outputPath = getPackageName().replace('.', File.separatorChar);
        // File outputDir = new File(getOutputDir(), outputPath);
        args.add(getOutputDir().getAbsolutePath());

        args.add(JNAeratorCommandLineArgs.OptionDef.ForceOverwrite.clSwitch);
        // args.add(JNAeratorCommandLineArgs.OptionDef.Verbose.clSwitch);
        args.add("-v");

        for (String d : define)
            args.add("-D" + d);
        for (String u : undefine)
            args.add("-U" + u);

        args.addAll(getExtraArgs());

        DefaultGroovyMethods.deleteDir(outputDir);
        outputDir.mkdirs();
        getLogger().info("Invoking jnaerator " + args);

        JNAerator.main(args.toArray(new String[args.size()]));
    }
}
