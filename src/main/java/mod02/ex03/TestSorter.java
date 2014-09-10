package mod02.ex03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class TestSorter {

	public static void main(String[] args) {

		// create collection to sort
		List<String> words = new ArrayList<String>();
		for (int i = 0; i < 1_000_000; i++) {
			words.add(String.format("X%06d", i));
		}
		Collections.shuffle(words);
		
		long start = System.currentTimeMillis();
		Collections.sort(words);
		long stop = System.currentTimeMillis();
		System.out.println("Standard sorting time: " + (stop - start) + " ms");

		Collections.shuffle(words);
		start = System.currentTimeMillis();
		Sorter<String> sorter = new Sorter<String>(words, 0, words.size());
		ForkJoinPool pool = new ForkJoinPool(4);
		pool.invoke(sorter);
		stop = System.currentTimeMillis();
		System.out.println("F&J sorting time: "+ (stop - start) + " ms");
//		System.out.println(sorter.getSource());
	}

}
