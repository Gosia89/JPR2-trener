package mod06._ex03;

public class TestCache {

	public static void main(String[] args) {
		SoftCache<Integer, Lob> cache = new SoftCache<>();
		for (int i = 0; i < 100; i++) {
			Lob lob = new Lob(i, 10_000_000);
			System.out.print("putting " + lob);
			cache.put(i, lob);
			System.out.println("   size=" + cache.size());
		}
	}

}
