package mod02.ex03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Sorter<E extends Comparable<E>> extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private int SMALL_ENOUGH = 100_000;
	private final List<E> source;
	private final List<E> destination;

	public Sorter(List<E> source, int start, int end) {
		this.source = source.subList(start, end);
		this.destination = new ArrayList<E>();
	}

	public List<E> getSource() {
		return source;
	}

	@Override
	protected void compute() {
		int size = source.size();
		if (size < SMALL_ENOUGH) {
			Collections.sort(source);
		} else {
			int mid = size / 2;
			Sorter<E> left = new Sorter<E>(source, 0, mid);
			Sorter<E> right = new Sorter<E>(source, mid, size);
			invokeAll(left, right);
			merge(left, right);
		}
	}

	private void merge(Sorter<E> left, Sorter<E> right) {
		int leftIndex = 0;
		int rightIndex = 0;
		while (leftIndex < left.source.size()
				&& rightIndex < right.source.size()) {
			E l = left.source.get(leftIndex);
			E r = right.source.get(rightIndex);
			if (l.compareTo(r) < 0) {
				destination.add(l);
				leftIndex++;
			} else {
				destination.add(r);
				rightIndex++;
			}
		}
		while (leftIndex < left.source.size()) {
			destination.add(left.source.get(leftIndex++));
		}
		while (rightIndex < right.source.size()) {
			destination.add(right.source.get(rightIndex++));
		}
		for (int i = 0, size = destination.size(); i < size; i++) {
			source.set(i, destination.get(i));
		}
	}
}
