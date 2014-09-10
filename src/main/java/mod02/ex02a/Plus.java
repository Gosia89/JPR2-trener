package mod02.ex02a;

public class Plus implements Runnable {
	private Counter counter;
	private int repeats;

	public Plus(Counter counter, int repeats) {
		this.counter = counter;
		this.repeats = repeats;
	}

	@Override
	public void run() {
		for (int i = 0; i < repeats; i++)
			counter.increment();
	}
}
