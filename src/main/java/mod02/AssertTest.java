package mod02;

public class AssertTest {

	public static void checkAssertionsEnabled() {
		boolean enabled = false;
		assert (enabled = true);
		if (!enabled) {
			System.out.println("Run with assertions enabled!!!");
			System.exit(0);
		}
	}
}
