package mod06._ex02;

public class TestCache {

	public static void main(String[] args) {
		SoftCache<Integer, Lob> cache = new SoftCache<>();
		for (int i = 0; i < 100; i++) {
			Lob lob = new Lob(i, 10_000_000);
			cache.put(i,lob);
		}
	}

}
