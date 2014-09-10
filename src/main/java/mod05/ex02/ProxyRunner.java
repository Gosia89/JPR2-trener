package mod05.ex02;

public class ProxyRunner {

	public static void main(String[] args) {
		Operacje kalkulator = Kalkulator.utworzKalkulator();
		kalkulator.dodaj(2, 4);
		kalkulator.dodaj(1, 0, 5, 6);
		kalkulator.pomnoz(4, 2);
		kalkulator.pomnoz(2, 3, 4);
	}
}
