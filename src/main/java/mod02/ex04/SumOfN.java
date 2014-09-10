package mod02.ex04;

import java.util.concurrent.*;

public class SumOfN {
	private static long N = 1_000_000;
	private static final int NUM_THREADS = 10;

	private static class RecursiveSumOfN extends RecursiveTask<Long> {
		private static final long serialVersionUID = -8557688051773389743L;
		long from, to;

		public RecursiveSumOfN(long from, long to) {
			this.from = from;
			this.to = to;
		}

		public Long compute() {
			if ((to - from) <= N / NUM_THREADS) {
				long localSum = 0;
				for (long i = from; i <= to; i++) {
					localSum += i;
				}
				return localSum;
			} else {
				long mid = (from + to) / 2;
				RecursiveSumOfN firstHalf = new RecursiveSumOfN(from, mid);
				firstHalf.fork();
				RecursiveSumOfN secondHalf = new RecursiveSumOfN(mid + 1, to);
				long resultSecond = secondHalf.compute();
				return firstHalf.join() + resultSecond;
			}
		}
	}

	public static void main(String[] args) {
		long computedSum = 0;
		ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);
		long start = System.nanoTime();
		for (int i = 0; i < 1_000; i++)
			computedSum = pool.invoke(new RecursiveSumOfN(0, N));
		long stop = System.nanoTime();

		long formulaSum = (N * (N + 1)) / 2;
		System.out.println("Sum for range 1.." + N);
		System.out.println("Computed sum = " + computedSum);
		System.out.println("Formula  sum = " + formulaSum);
		System.out.println("\nExecution time: "
				+ TimeUnit.NANOSECONDS.toMillis(stop - start) + " ms");
	}
}