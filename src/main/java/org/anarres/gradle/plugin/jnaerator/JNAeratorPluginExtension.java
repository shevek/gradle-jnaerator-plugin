package org.anarres.gradle.plugin.jnaerator;

import java.io.File;
import javax.annotation.Nonnull;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.file.UnionFileCollection;
import org.gradle.api.internal.file.collections.SimpleFileCollection;

/**
 *
 * @author shevek
 */
public class JNAeratorPluginExtension {

    public static final String NAME = "jnaerator";
    private File outputDir;
    private String outputPackage;
    private String libraryName;
    private FileCollection headerFiles = new SimpleFileCollection();

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

    @Nonnull
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(@Nonnull String libraryName) {
        this.libraryName = libraryName;
    }

    public void setHeaderFiles(@Nonnull FileCollection headerFiles) {
        this.headerFiles = headerFiles;
    }

    @Nonnull
    public FileCollection getHeaderFiles() {
        return headerFiles;
    }

    public void headerFiles(@Nonnull FileCollection headerFiles) {
        this.headerFiles = new UnionFileCollection(this.headerFiles, headerFiles);
    }

}
