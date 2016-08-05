import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommands;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class Shell {
    // コマンド本体のオプション
    @Option(name="-v", aliases="--version", usage="print version")
        private boolean versionFlag;
    @Option(name="-h", aliases="--help", usage="print usage message and exit")
        private boolean usageFlag;

    @Argument(
        usage="subcommand",
        handler = SubCommandHandler.class
    )

    // サブコマンド定義
    @SubCommands({
        @SubCommand(name = "sub1", impl = Sub1Command.class),
        @SubCommand(name = "sub2", impl = Sub2Command.class)
    })
    private Command command;

    // コマンド本体
    public static void main(String[] args) {
        Shell shell = new Shell();

        CmdLineParser parser = new CmdLineParser(shell);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println(e);
            System.out.println();
            e.getParser().printUsage(System.out);
            // ここでサブコマンドだけでも取得できればサブコマンドのstatic printUsage呼べる
            return;
        }

        if (shell.usageFlag) {
            printUsage();
            System.out.println("Options:");
            parser.printUsage(System.out);
            return;
        }

        if (shell.versionFlag) {
            printVersion();
            return;
        }

        // サブコマンドが指定されていたら実行する
        if(shell.command != null){
            shell.command.execute();
            return;
        }
        printUsage();
    }

    private static void printUsage(){
        System.out.println("Usage:");
        System.out.println(" Shell [options]");
        System.out.println(" Shell subcommand [subcommand options]");
        System.out.println();
        System.out.println("Subcommand:");
        System.out.println(" sub1: hogehoge");
        System.out.println(" sub2: hugahuga");
        System.out.println();
    }

    private static void printVersion(){
        System.out.println("sample version 0.0.1");
    }

    /**
     * コマンド
     */
    public static interface Command {
        public void execute();
        // public static void printUsage();
    }

    /**
     * Sub1
     */
    public static class Sub1Command implements Command {
        @Option(name="-i", aliases="--input", required = true, usage="input file path")
            private String inputPath;
        @Option(name="-o", aliases="--output", usage="output file path")
            private String outputPath;
        @Option(name="-h", aliases="--help", usage="print usage message and exit")
            private boolean usageFlag;

        @Override
        public void execute() {
            if (usageFlag) {
                printUsage();
                return;
            }

            System.out.println("Sub1");
            System.out.println("input  : " + inputPath);
            System.out.println("output : " + outputPath);
        }

        public void printUsage(){
            Sub1Command sub1 = new Sub1Command();
            CmdLineParser parser = new CmdLineParser(sub1);
            System.out.println("Sub1 help");
            System.out.println("Options:");
            parser.printUsage(System.out);
        }
    }

    /**
     * Sub2
     */
    public static class Sub2Command implements Command {
        @Override
        public void execute() {
            System.out.println("Sub2");
        }
    }
}
