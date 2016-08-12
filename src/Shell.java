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
            if(args.length > 0){
				CmdLineParser subcmd = new CmdLineParser(shell);
				try {
					// �擪���������ēx��́B
					// �T�u�R�}���h�ɕK�{�������w�肳��Ă���ƌ��ǂ����ŗ�O���o�Ă��܂��B�v����
					subcmd.parseArgument(args[0]);
					if(shell.command != null){
						// �����ŃT�u�R�}���h��static printUsage�Ăׂ�
						System.out.println(shell.command.getClass());
						return;
					}
				} catch (CmdLineException e1) {
					// �T�u�R�}���h�̎w���肩�A���C���R�}���h�̃I�v�V�������
					// ���s���p������Main��Usage�o��
				}
            }
            System.out.println("usage:");
            e.getParser().printSingleLineUsage(System.out);
            System.out.println();
            e.getParser().printUsage(System.out);
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
