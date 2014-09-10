package mod02.ex01;

public class Max {
	public static int findMax(int[] array, int first, int last) {
		int max = array[first];
		for (int i = first + 1; i <= last; i++) {
			if (array[i] > max)
				max = array[i];
		}
		return max;
	}
}
