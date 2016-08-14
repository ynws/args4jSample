import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommands;

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
			// サブコマンドが指定されていたら実行する
			if(shell.command != null){
				shell.command.execute();
				return;
			}
        } catch (CmdLineException e) {
            System.out.println(e);
            System.out.println();
            
            // コマンドごとのUsageを出すための処理。汚い
            if(args.length > 0){
				CmdLineParser subcmd = new CmdLineParser(shell);
				try {
					// 先頭引数だけ再度解析。
					// サブコマンドに必須引数が指定されていると結局ここで例外が出てしまう。要検討
					subcmd.parseArgument(args[0]);
					shell.command.printUsage();
					return;
				} catch (CmdLineException e1) {
					// サブコマンドの指定誤りか、メインコマンドのオプション誤り
					// MainのUsage出す
					printUsage(parser);
					return;
				}
            }
            // コマンド再解析しなくても以下の記述でもサブコマンドの単純なUsageが出せるが、ユーザ指定のコメント等は出せない
            // System.out.println("sub auto usage:");
            // e.getParser().printSingleLineUsage(System.out);
            // System.out.println();
            // e.getParser().printUsage(System.out);
            // return;
        }

        if (shell.usageFlag) {
            printUsage(parser);
            return;
        }

        if (shell.versionFlag) {
            printVersion();
            return;
        }

        printUsage(parser);
    }

    private static void printUsage(CmdLineParser parser){
        System.out.println("Main Usage:");
        System.out.println(" Shell [options]");
        System.out.println(" Shell subcommand [subcommand options]");
		System.out.println("Options:");
		parser.printUsage(System.out);
    }

    private static void printVersion(){
        System.out.println("sample version 0.0.1");
    }
}
