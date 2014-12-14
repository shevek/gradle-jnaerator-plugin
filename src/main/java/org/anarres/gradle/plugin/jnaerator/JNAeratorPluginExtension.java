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
    private String libraryName;
    private String packageName;
    private Object[] headerFiles;

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(@Nonnull String libraryName) {
        this.libraryName = libraryName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setHeaderFiles(@Nonnull Object[] headerFiles) {
        this.headerFiles = headerFiles;
    }

    public Object[] getHeaderFiles() {
        return headerFiles;
    }
}