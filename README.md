# Introduction

This is a gradle plugin which wraps JNAerator for automatically
generating JNA bindings for native libraries.

# Usage:

Gradle 1.x-2.1:

```
buildscript {
    dependencies {
        classpath "org.anarres.gradle:gradle-jnaerator-plugin:1.0.0"
		// https://github.com/etiennestuder/gradle-plugindev-plugin/issues/11
        classpath "com.nativelibs4java:jnaerator:0.11"
    }
}

apply plugin: 'org.anarres.jnaerator'
```

Gradle 2.2+:

```
plugins {
    id 'org.anarres.jnaerator' version '1.0.0'
}
```

Then:

```
jnaerator {
	libraryName 'udev'
	packageName 'org.anarres.jna.udev'
	headerFiles "/usr/include/libudev.h"
	// runtimeMode "JNAerator"
}
```

# Example:

See https://github.com/shevek/udev4j .

# API Documentation

The [JavaDoc API](http://shevek.github.io/gradle-jnaerator-plugin/docs/javadoc/)
is also available.

