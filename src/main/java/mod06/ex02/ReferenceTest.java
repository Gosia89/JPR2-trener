package mod06.ex02;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

// solution from 'Thinking in Java'

public class ReferenceTest {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();
	private static final int TO_ADD = 5;

	public static void checkQueue() {
		Reference<? extends VeryBig> inq = rq.poll();
		if (inq != null) {
			System.out.println("In queue " + inq.get());
		}
	}

	public static void main(String[] args) {
		LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
		for (int i = 0; i < TO_ADD; i++) {
			sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
			System.out.println("Just created " + sa.getLast().get());
			checkQueue();
		}
		System.out.println();

		LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
		for (int i = 0; i < TO_ADD; i++) {
			wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
			System.out.println("Just created " + wa.getLast().get());
			checkQueue();
		}
		System.out.println();

		// weak and soft can be created without queue
		SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig(
				"Soft "));
		System.out.println("Just created " + s.get());
		WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig(
				"Weak "));
		System.out.println("Just created " + w.get());
		System.out.println();

		System.out.println("--------- executing System.gc()\n");
		System.gc();

		VeryBig [] vb = new VeryBig[100000000];

		LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
		for (int i = 0; i < TO_ADD; i++) {
			pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i),
					rq));
			System.out.println("Just created " + pa.getLast());
			checkQueue();
		}
		System.out.println();
	}
}
