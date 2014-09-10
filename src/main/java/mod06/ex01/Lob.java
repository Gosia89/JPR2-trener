package mod06.ex01;

public class Lob {
	private long id;
	private byte[] array;

	public Lob(long id, int size) {
		this.id = id;
		this.array = new byte[size];
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "lob #" + id;
	}

	@Override
	protected void finalize() {
//		System.out.println("finalizing lob #" + id);
	}
}
