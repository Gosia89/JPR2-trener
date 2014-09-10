package mod01.ex03;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFile extends SimpleFileVisitor<Path> {
	private final Path searchedFile;				// file to search for
	private List<Path> found = new ArrayList<>();	// found files collection

	public SearchFile(Path searchedFile) {
		this.searchedFile = searchedFile;
	}


	/* methods: 
	 * 		preVisitDirectory
	 * 		visitFileFailed 
	 * are not overridden
	 */

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e)
			throws IOException {
		System.out.println("Visited: " + dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		Path name = file.getFileName();
		if (name != null && name.equals(searchedFile)) {
			found.add(file);
		}
		return FileVisitResult.CONTINUE;
	}

	// method for presenting search results
	public void listFoundFiles() {
		System.out.print("\nSearched file '" + searchedFile	+ "' was ");
		if (found.isEmpty()) {
			System.out.println("not found");
			return;
		}
		System.out.println("found in:");
		try {
			for (Path file : found) {
				System.out.println("\t" + file.toRealPath().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
