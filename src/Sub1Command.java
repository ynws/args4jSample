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
		System.out.println("Sub1 Usage");
		System.out.println("Options:");

		Sub1Command sub1 = new Sub1Command();
		CmdLineParser parser = new CmdLineParser(sub1);
		parser.printUsage(System.out);
	}
}
