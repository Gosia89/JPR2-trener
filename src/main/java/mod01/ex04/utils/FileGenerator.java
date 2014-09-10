package mod01.ex04.utils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileGenerator {
	public static final String IN_FILE = "inFile.txt";
	public static final String OUT_FILE = "outFile.txt";
	public static final int LINE_SIZE = 2 * 1024 * 1024;
	public static final int LINES = 100;

	private static final byte[] LINE = new byte[LINE_SIZE];
	private static final Random RANDOM = new Random();

	public static void main(String[] args) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(IN_FILE));

		for (int i = 0; i < LINES; i++) {
			System.out.printf("Produkuję linię: %3d%n", i + 1);
			generateLine();
			out.write(LINE);
			out.write('\n');
		}
		out.close();
	}

	private static void generateLine() {
		// pierwszy bajt to licznik
		LINE[0]++;
		// System.out.println(LINE[0]);
		int modifications = RANDOM.nextInt(100);

		for (int i = 0; i < modifications; i++) {
			// zmieniamy losowy bajt poza pierwszym
			int modified = RANDOM.nextInt(LINE_SIZE - 1) + 1;
			LINE[modified] = (byte) RANDOM.nextInt();
			// System.out.println(LINE[modified]);
		}
	}
}
