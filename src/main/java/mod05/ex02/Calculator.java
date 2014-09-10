package mod05.ex02;

public class Calculator implements Operations {

	@Override
	public void add(int... x) {
		int result = x[0];
		System.out.print(x[0]);
		for (int i = 1; i < x.length; i++) {
			result += x[i];
			System.out.print(" + " + x[i]);
		}
		System.out.println(" = " + result);
	}

	@Override
	public void multiply(int... x) {
		int result = x[0];
		System.out.print(x[0]);
		for (int i = 1; i < x.length; i++) {
			result *= x[i];
			System.out.print(" * " + x[i]);
		}
		System.out.println(" = " + result);
	}
}
