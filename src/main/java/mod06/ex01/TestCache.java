package mod06.ex01;

public class TestCache {

	public static void main(String[] args) {
		SoftCache<Lob> cache = new SoftCache<>();
		for (int i = 0; i < 100; i++) {
			Lob lob = new Lob(i, 10_000_000);
			System.out.print("creating " + lob);
			cache.put(lob);
			System.out.println("   current cache size = " + cache.size());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
