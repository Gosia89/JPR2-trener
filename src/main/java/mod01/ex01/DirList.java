package mod01.ex01;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirList {

	public static void main(String[] args) {
		Path home = Paths.get(System.getProperty("user.home"));
		listSubDirsOf(home);
	}

	public static void listSubDirsOf(Path path) {
		// filter definition (only dirs are accepted)
		DirectoryStream.Filter<Path> dirFilter = new DirectoryStream.Filter<Path>() {

			public boolean accept(Path path) throws IOException {
				return (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
			}

		};

		System.out.println("SUBDIRECTORIES LIST OF: " + path.toAbsolutePath().toString() + "\n");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, dirFilter)) {
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e.toString());
		}

	}
}
