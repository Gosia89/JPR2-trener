package mod01.ex04.utils;

public class Stopper {
	private long start;
	private int repeats;
	private String action;

	public void start(String methodName) {
		start(methodName, 1);
	}

	public void start(String action, int repeats) {
		// start = System.currentTimeMillis();
		start = System.nanoTime();
		this.action = action;
		this.repeats = repeats;
	}

	public void start() {
		// start = System.currentTimeMillis();
		start = System.nanoTime();
	}

	public long stop() {
		// return System.currentTimeMillis() - start;
		return System.nanoTime() - start;
	}

	public long stop(boolean syso) {
		// long duration = System.currentTimeMillis() - start;
		long duration = System.nanoTime() - start;
		if (syso) {
			stop(duration);
		}
		return duration;
	}

	public void stop(long duration) {
		duration /= 1000000; // time in ms
		System.out.printf("%50s | %7d ms | %12d | %.2e ms%n", action, duration,
				repeats, (float) duration / repeats);
	}

	public static void header(boolean syso) {
		if (syso) {
			for (int i = 0; i <= 50; i++)
				System.out.print("-");
			System.out.println("+------------+--------------+--------------");
			for (int i = 0; i <= 50; i++)
				System.out.print(" ");
			System.out.println("| Total time | Execution(s) | Average time");
			for (int i = 0; i <= 50; i++)
				System.out.print("-");
			System.out.println("+------------+--------------+--------------");
		}
	}
}
