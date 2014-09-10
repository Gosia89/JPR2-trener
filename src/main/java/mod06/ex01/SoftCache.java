package mod06.ex01;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftCache<E> {
	private List<SoftReference<E>> cache;
	private ReferenceQueue<E> rq;

	public SoftCache() {
		this.cache = new ArrayList<SoftReference<E>>();
		this.rq = new ReferenceQueue<E>();
		Thread t = new Thread(new Cleaner());
		t.setDaemon(true);
		t.start();
	}

	public synchronized void put(E element) {
		cache.add(new SoftReference<E>(element, rq));
	}

	public synchronized E get(int i) {
		if (i<cache.size()) {
			SoftReference<E> ref = cache.get(i);
			return ref.get();
		}
		return null;
	}

	public synchronized int size() {
		return cache.size();
	}

	private class Cleaner implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					Reference<? extends E> ref = rq.remove(); // blocking
					synchronized (SoftCache.this) {
						cache.remove(ref);
					}
				}
			} catch (InterruptedException e) {
				System.out.println("Cleaner has been interrupted...");
			}
		}

	}
}
