public class Sub2Command implements Command {
	@Override
	public void execute() {
		System.out.println("Sub2");
	}
	
	@Override
	public void printUsage(){
		System.out.println("Sub2 usage");
	}
}
