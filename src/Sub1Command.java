import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Sub1Command implements Command {
	@Option(name="-i", aliases="--input", usage="input file path")
	private String inputPath;
	@Option(name="-o", aliases="--output", usage="output file path")
	private String outputPath;
	@Option(name="-h", aliases="--help", usage="print usage message and exit")
	private boolean usageFlag;

	@Override
	public void execute() throws CmdLineException {
		if (usageFlag) {
			printUsage();
			return;
		}
		
		// 必須パラメタのチェック
		if(inputPath == null){
			Sub1Command sub1 = new Sub1Command();
			CmdLineParser parser = new CmdLineParser(sub1);
			throw new CmdLineException(parser, "input file path error", null);
		}

		System.out.println("Sub1");
		System.out.println("input  : " + inputPath);
		System.out.println("output : " + outputPath);
	}

	public void printUsage(){
		System.out.println("Sub1 Usage:");
		System.out.println();

		Sub1Command sub1 = new Sub1Command();
		CmdLineParser parser = new CmdLineParser(sub1);
		System.out.print(" Shell sub1 ");
		parser.printSingleLineUsage(System.out);
		System.out.println();
		System.out.println();
		System.out.println("Options:");
		parser.printUsage(System.out);
	}
}
