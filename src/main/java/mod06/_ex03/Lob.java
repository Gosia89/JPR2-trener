package mod06._ex03;

public class Lob {
	private long id;
	private byte[] array;

	public Lob(long id, int size) {
		this.id = id;
		this.array = new byte[size];
	}

	@Override
	public String toString() {
		return "Lob #" + id;
	}

}
