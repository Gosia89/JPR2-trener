package mod02.ex02c;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Counter {

	private volatile int counter = 0;

	private AtomicIntegerFieldUpdater<Counter> updater = AtomicIntegerFieldUpdater
			.newUpdater(Counter.class, "counter");

	public void increment() {
		updater.getAndIncrement(this);
	}

	public void decrement() {
		updater.getAndDecrement(this);
	}

	public int getCounter() {
		return counter;
	}
}