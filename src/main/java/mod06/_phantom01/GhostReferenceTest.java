package mod06._phantom01;

import java.lang.ref.ReferenceQueue;

import mod06._soft02.LargeObject;


public class GhostReferenceTest {
	private static final int NUMBER_OF_REFERENCES = 10;

	public static void main(String[] args) {
		final ReferenceQueue queue = new ReferenceQueue();
		new Thread() {
			{
				setDaemon(true);
				start();
			}

			public void run() {
				try {
					while (true) {
						GhostReference ref = (GhostReference) queue.remove();
						LargeObject obj = (LargeObject) ref.getReferent();
						System.out.println("GHOST " + obj.getId());
						ref.clear();
					}
				} catch (InterruptedException e) {
				}
			}
		};
		for (int i = 0; i < NUMBER_OF_REFERENCES; i++) {
			System.out.println("NEW   " + i);
			new GhostReference(new LargeObject(i), queue);
		}
		byte[][] buf = new byte[1024][];
		System.out.println("Allocating until OOME...");
		try {
			for (int i = 0; i < buf.length; i++) {
				buf[i] = new byte[1024 * 1024];
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace(System.out);
		}
	}
}
