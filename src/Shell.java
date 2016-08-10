import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommands;

public class Shell {
    // �R�}���h�{�̂̃I�v�V����
    @Option(name="-v", aliases="--version", usage="print version")
        private boolean versionFlag;
    @Option(name="-h", aliases="--help", usage="print usage message and exit")
        private boolean usageFlag;

    @Argument(
        usage="subcommand",
        handler = SubCommandHandler.class
    )

    // �T�u�R�}���h��`
    @SubCommands({
        @SubCommand(name = "sub1", impl = Sub1Command.class),
        @SubCommand(name = "sub2", impl = Sub2Command.class)
    })
    private Command command;

    // �R�}���h�{��
    public static void main(String[] args) {
        Shell shell = new Shell();

        CmdLineParser parser = new CmdLineParser(shell);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.out.println(e);
            System.out.println();
            System.out.println("usage:");
            parser.printSingleLineUsage(System.out);
            System.out.println();
            e.getParser().printUsage(System.out);
            // �����ŃT�u�R�}���h�����ł��擾�ł���΃T�u�R�}���h��static printUsage�Ăׂ�
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

        // �T�u�R�}���h���w�肳��Ă�������s����
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
}
