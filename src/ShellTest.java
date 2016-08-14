
import org.junit.Test;

public class ShellTest {

	@Test
	public void test() {

		sub(new String[] { "-v" });
		sub(new String[] { "-h" });
		sub(new String[] { "-s" });
		sub(new String[] { "sub1", "-i", "aaa" });
		sub(new String[] { "sub1", "-a" });
		sub(new String[] { "sub3" });
		sub(new String[] { "sub1"});
	}
	
	private void sub(String[] args){
		StringBuilder builder = new StringBuilder();
		for(String str : args) {
			builder.append(str).append(",");
		}
		System.out.println("########################################");
		System.out.println("## " + builder.toString());
		System.out.println("########################################");
		Shell.main(args);
	}

}
