package org.anarres.gradle.plugin.jnaerator;

import com.ochafik.lang.jnaerator.JNAerator;
import com.ochafik.lang.jnaerator.JNAeratorCommandLineArgs;
import com.ochafik.lang.jnaerator.JNAeratorConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author shevek
 */
public class JNAeratorTest {

    @Test
    public void testJNAerator() throws Exception {
        List<String> args = new ArrayList<String>();
        args.add(JNAeratorCommandLineArgs.OptionDef.CurrentLibrary.clSwitch);
        args.add("xcb");
        args.add("/usr/include/xcb/xcb.h");
        args.add(JNAeratorCommandLineArgs.OptionDef.OutputMode.clSwitch);
        args.add(JNAeratorConfig.OutputMode.Directory.name());
        args.add(JNAeratorCommandLineArgs.OptionDef.Runtime.clSwitch);
        args.add(JNAeratorConfig.Runtime.JNA.name());
        args.add(JNAeratorCommandLineArgs.OptionDef.OutputDir.clSwitch);
        args.add("build/test/org/anarres/jnaerator/test");
        args.add(JNAeratorCommandLineArgs.OptionDef.Verbose.clSwitch);
        JNAerator.main(args.toArray(new String[args.size()]));
    }
}
