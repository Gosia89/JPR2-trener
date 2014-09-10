package mod06._ex02;

import java.util.HashMap;
import java.lang.ref.SoftReference;

public class SoftCache<K, V> {
	private HashMap<K, SoftReference<V>> cache = new HashMap<>();

	public Object get(K key) {
		SoftReference<V> ref = cache.get(key);
		return (ref != null) ? ref.get() : null;
	}

	public Object put(K key, V value) {
		SoftReference<V> ref = cache.put(key, new SoftReference<V>(value));
		
		if (null == ref) {
			return null;
		}
		V oldValue = ref.get();
//		ref.clear();
		return oldValue;
	}

	public Object remove(K key) {
		SoftReference<V> ref = cache.remove(key);
		if (null == ref) {
			return null;
		}
		V oldValue = ref.get();
//		ref.clear();
		return oldValue;
	}
}