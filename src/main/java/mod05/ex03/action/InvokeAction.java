package mod05.ex03.action;

import java.util.concurrent.TimeUnit;

public class InvokeAction implements InvokeInterface {
	private long start;

	@Override
	public void beforeInvoke(String methodName) {
		System.out.print("Method " + methodName + ": [started...]  ");
		start = System.nanoTime();
	}

	@Override
	public void afterInvoke(String methodName) {
		long stop = System.nanoTime();
		System.out.printf("  [stopped...]  Run time: %7d ms%n",
				TimeUnit.NANOSECONDS.toMillis(stop - start));
	}
}
