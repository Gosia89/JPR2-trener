package mod02.ex01;

import java.util.Random;

public class Data {
	public static int[] createData(int size) {
		int[] array = new int[size];
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = r.nextInt(size);
		}
		return array;
	}
}
