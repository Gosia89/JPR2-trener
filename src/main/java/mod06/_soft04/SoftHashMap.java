package mod06._soft04;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class SoftHashMap<K, V> extends AbstractMap<K, V> implements
		Serializable {
	private static final long serialVersionUID = 1L;

	private final Map<K, SoftReference<V>> cache = new HashMap<K, SoftReference<V>>();
	private final Map<SoftReference<V>, K> reverse = new HashMap<SoftReference<V>, K>();

	private final ReferenceQueue<V> queue = new ReferenceQueue<V>();

	public V get(Object key) {
		processQueue();
		V result = null;
		SoftReference<V> softRef = cache.get(key);
		if (softRef != null) {
			result = softRef.get();
			if (result == null) {
				cache.remove(key);
				reverse.remove(softRef);
			}
		}
		return result;
	}

	private void processQueue() {
		Reference<? extends V> sv;
		while ((sv = queue.poll()) != null) {
			cache.remove(reverse.remove(sv));
		}
	}

	public V put(K key, V value) {
		processQueue();
		SoftReference<V> soft_ref = new SoftReference<V>(value, queue);
		reverse.put(soft_ref, key);
		SoftReference<V> result = cache.put(key, soft_ref);
		if (null == result) {
			return null;
		}
		reverse.remove(result);
		return result.get();
	}

	public V remove(Object key) {
		processQueue();
		SoftReference<V> result = cache.remove(key);
		if (null == result) {
			return null;
		}
		reverse.remove(result);
		return result.get();
	}

	public void clear() {
		cache.clear();
		reverse.clear();
	}

	public int size() {
		processQueue();
		return cache.size();
	}

	public Set<Entry<K, V>> entrySet() {
		processQueue();
		Set<Entry<K, V>> result = new LinkedHashSet<Entry<K, V>>();
		for (final Entry<K, SoftReference<V>> entry : cache.entrySet()) {
			final V value = entry.getValue().get();
			if (value != null) {
				result.add(new Entry<K, V>() {
					public K getKey() {
						return entry.getKey();
					}

					public V getValue() {
						return value;
					}

					public V setValue(V v) {
						entry.setValue(new SoftReference<V>(v, queue));
						return value;
					}
				});
			}
		}
		return result;
	}
}