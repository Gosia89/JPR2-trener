package mod05.ex03.domain;

import java.util.Random;
import static java.util.concurrent.TimeUnit.*;

public class MyServiceBean implements MyService {

	@Override
	public void businessMethodA() {
		Random r = new Random();
		int time = r.nextInt(1000);
		try {
			MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("A");
	}

	@Override
	public void businessMethodB() {
		Random r = new Random();
		int time = r.nextInt(1000);
		try {
			MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("B");
	}

	@Override
	public void businessMethodC() {
		Random r = new Random();
		int time = r.nextInt(1000);
		try {
			MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("C");
	}
}