import org.kohsuke.args4j.CmdLineException;

/**
 * �R�}���h
 */
public interface Command {
	public void execute() throws CmdLineException;
	public void printUsage();
	// public static void printUsage();
}
