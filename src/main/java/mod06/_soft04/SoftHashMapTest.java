package mod06._soft04;

import java.util.HashMap;
import java.util.Map;

public class SoftHashMapTest {
	private static void print(Map<String, Integer> map) {
		System.out.println("One=" + map.get("One"));
		System.out.println("Two=" + map.get("Two"));
		System.out.println("Three=" + map.get("Three"));
		System.out.println("Four=" + map.get("Four"));
		System.out.println("Five=" + map.get("Five"));
	}

	private static void testMap(Map<String, Integer> map)
			throws InterruptedException {
		System.out.println("Testing " + map.getClass());
		map.put("One", new Integer(1));
		map.put("Two", new Integer(2));
		map.put("Three", new Integer(3));
		map.put("Four", new Integer(4));
		map.put("Five", new Integer(5));
		print(map);
		Thread.sleep(2000);
		print(map);
		byte[][] buf = new byte[1024][];
		System.out.println("Allocating until OOME...");
		try {
			for (int i = 0; i < buf.length; i++) {
				buf[i] = new byte[1024 * 1024];
			}
		} catch (OutOfMemoryError ex) {
			ex.printStackTrace(System.out);
		}
		print(map);
	}

	public static void main(String[] args) throws InterruptedException {
		testMap(new HashMap<String, Integer>());
		testMap(new SoftHashMap<String, Integer>());
	}
}
