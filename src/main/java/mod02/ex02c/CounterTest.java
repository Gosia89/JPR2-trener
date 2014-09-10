package mod02.ex02c;

import static mod02.AssertTest.*;

public class CounterTest {

	public static void main(String[] args) {
		checkAssertionsEnabled();

		final int REPEATS = 1_000_000; // # of incrementations/decrementations
		int loop = 0;

		while (true) {
			Counter counter = new Counter();
			Thread plus = new Thread(new Plus(counter, REPEATS));
			Thread minus = new Thread(new Minus(counter, REPEATS));
			plus.start();
			minus.start();
			try {
				plus.join();
				minus.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			assert (counter.getCounter() == 0) : "\nloop #" + loop
					+ ": counter = " + counter.getCounter();
			System.out.println("loop #" + loop++ + " OK");
		}
	}
}
