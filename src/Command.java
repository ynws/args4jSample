import org.kohsuke.args4j.CmdLineException;

/**
 * ƒRƒ}ƒ“ƒh
 */
public interface Command {
	public void execute() throws CmdLineException;
	public void printUsage();
	// public static void printUsage();
}
