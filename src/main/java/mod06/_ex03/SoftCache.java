package mod06._ex03;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftCache<K, V> {
	private Map<K, SoftReference<V>> cache = new ConcurrentHashMap<>();
	private ReferenceQueue<V> rq = new ReferenceQueue<>();

	public SoftCache() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Reference<? extends V> ref = rq.remove(); // blocking
						synchronized (cache) {
							for (Map.Entry<K, SoftReference<V>> entry : cache.entrySet()) {
								if (entry.getValue() == ref) {
									cache.remove(entry.getKey());
								}
							}
							// System.out.println("Removing " + ref);
						}
					}
				} catch (InterruptedException e) {
					System.out.println("Cleaner thread has been interrupted...");
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	public V get(K key) {
//		synchronized (cache) {
			SoftReference<V> ref = cache.get(key);
			return (ref != null) ? ref.get() : null;
//		}
	}

	public V put(K key, V value) {
//		synchronized (cache) {
			SoftReference<V> ref = cache.put(key, new SoftReference<V>(value,
					rq));
			return getValue(ref);
//		}
	}

	public V remove(K key) {
//		synchronized (cache) {
			SoftReference<V> ref = cache.remove(key);
			return getValue(ref);
//		}
	}

	private V getValue(SoftReference<V> ref) {
		if (null == ref) {
			return null;
		}
		V oldValue = ref.get();
		ref.clear();
		return oldValue;
	}

	public int size() {
		return cache.size();
	}
}