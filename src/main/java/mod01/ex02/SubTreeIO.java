package mod01.ex02;

import java.io.File;

public class SubTreeIO {

	public static void main(String[] args) {
		File path = new File("src");
		System.out.println("SUBTREE OF " + path.getAbsolutePath() + "\n");
		printSubTreeOf(path, "");
	}

	public static void printSubTreeOf(File file, String indent) {
		System.out.print(indent);
		if (file.isDirectory()) {
			System.out.println("+" + file.getName());
			for (File childfile : file.listFiles()) {
				printSubTreeOf(childfile, indent + "  ");
			}
		} else {
			System.out.println(file.getName());
		}
	}
}
