import static org.junit.Assert.*;

import org.junit.Test;

public class ShellTest {

	@Test
	public void test() {
		Shell.main(new String[] { "sub3", "-a" });
		Shell.main(new String[] { "sub1", "-a" });
	}

}
