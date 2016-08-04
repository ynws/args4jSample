import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class Shell {
    @Option(name="-v", aliases="--version", usage="print version")
        private boolean versionFlag;

    @Option(name="-h", aliases="--help", usage="print usage message and exit")
        private boolean usageFlag;

    @Argument(index=0, metaVar="script")
        private String script;

    @Argument(index=1, metaVar="arguments...", handler=StringArrayOptionHandler.class)
        private String[] arguments;

    public static void main(String[] args) {
        Shell shell = new Shell();

        CmdLineParser parser = new CmdLineParser(shell);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            return;
        }

        if (shell.usageFlag) {
            System.out.println("Usage:");
            System.out.println(" Shell [options]");
            System.out.println(" Shell [options] script [arguments]");
            System.out.println();
            System.out.println("Options:");
            parser.printUsage(System.out);
            return;
        }

        if (shell.versionFlag) {
            System.out.println("Sample Program ver 1.0.0");
            return;
        }

        System.out.println("finish");
    }
}
