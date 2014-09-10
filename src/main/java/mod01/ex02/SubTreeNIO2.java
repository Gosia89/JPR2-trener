package mod01.ex02;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class SubTreeNIO2 {

	public static void main(String[] args) {
		Path path = Paths.get("src");
		System.out.println("SUBTREE OF: " + path.toAbsolutePath().toString() + "\n");
		printSubTreeOf(path);
	}

	private static void printSubTreeOf(Path path) {
		final String INDENT = "   ";
		final int INDENT_LENGTH = INDENT.length();
		try {
			Files.walkFileTree(path, new FileVisitor<Path>() {
				private StringBuilder indent = new StringBuilder();

				@Override
				public FileVisitResult visitFile(Path path,
						BasicFileAttributes attrs) {
					System.out.println(indent.toString() + path.getFileName());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					System.out.println(indent.toString() + "+" + dir.getFileName());
					indent.append(INDENT);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir,
						IOException exc) throws IOException {
					indent.delete(0, INDENT_LENGTH);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path dir,
						IOException exc) throws IOException {
					throw exc;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
