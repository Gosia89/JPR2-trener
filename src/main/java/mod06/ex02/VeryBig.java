package mod06.ex02;

public class VeryBig {
	private static final int SIZE = 10000;
	private String ident;

	@SuppressWarnings("unused")
	private long[] la = new long[SIZE];

	public VeryBig(String id) {
		ident = id;
	}

	@Override
	public String toString() {
		return ident;
	}

	@Override
	protected void finalize() {
		System.out.println("Finalize " + ident);
	}
}