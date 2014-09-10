package mod01.ex03;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;

public class SearchTest {
	public static void main(String[] args) {
		Path searchFile = Paths.get("SearchTest.java");
		Path startDir = Paths.get(".");
		startSearch(searchFile);
	}

	private static void startSearch(Path searchFile, Path... startDirs) {
		Iterable<Path> rootDirs = Arrays.asList(startDirs); // collection view

		SearchFile fileVisitor = new SearchFile(searchFile);
		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

		if (0 == startDirs.length) { // search everywhere
			rootDirs = FileSystems.getDefault().getRootDirectories();
		}

		for (Path root : rootDirs) {
			try {
				Files.walkFileTree(root, opts, Integer.MAX_VALUE, fileVisitor);
			} catch (IOException e) {
				System.out.println("Exception while walking through "
						+ root.getFileName());
				e.printStackTrace();
			}
		}
		fileVisitor.listFoundFiles();
	}
}