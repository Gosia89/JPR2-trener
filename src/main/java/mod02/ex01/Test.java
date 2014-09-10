package mod02.ex01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		final int SIZE = 20_000_000;
		final int[] data = Data.createData(SIZE);

		// classic algorithm
		long start = System.nanoTime();
		int max = Max.findMax(data, 0, SIZE - 1);
		long stop = System.nanoTime();
		System.out.println("max = " + max + " time = "
				+ TimeUnit.NANOSECONDS.toMillis(stop - start) + " ms");

		start = System.nanoTime();
		// splitting problem into 2 subtasks
		Callable<Integer> calc1 = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Max.findMax(data, 0, SIZE / 2 - 1);
			}
		};
		Callable<Integer> calc2 = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return Max.findMax(data, SIZE / 2, SIZE - 1);
			}
		};

		FutureTask<Integer> task1 = new FutureTask<Integer>(calc1);
		FutureTask<Integer> task2 = new FutureTask<Integer>(calc2);

		// delegating subtask into worker threads
		new Thread(task1).start();
		new Thread(task2).start();

		// blocking to get results
		try {
			int result1 = task1.get();
			int result2 = task2.get();
			max = (result1 > result2) ? result1 : result2;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		stop = System.nanoTime();
		System.out.println("max = " + max + " time = "
				+ TimeUnit.NANOSECONDS.toMillis(stop - start) + " ms");
	}
}
