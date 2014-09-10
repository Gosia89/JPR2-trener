package mod02.ex02c;

public class Minus implements Runnable {
	private Counter counter;
	private int repeats;

	public Minus(Counter counter, int repeats) {
		this.counter = counter;
		this.repeats = repeats;
	}

	@Override
	public void run() {
		for (int i = 0; i < repeats; i++)
			counter.decrement();
	}
}