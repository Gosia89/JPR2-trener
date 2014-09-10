package mod01.ex04;

import static mod01.ex04.utils.FileGenerator.IN_FILE;
import static mod01.ex04.utils.FileGenerator.OUT_FILE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import mod01.ex04.utils.Command;


public class PathCommand implements Command {

	@Override
	public void execute() throws IOException {
		Path in = Paths.get(IN_FILE);
		Path out = Paths.get(OUT_FILE);
		Files.copy(in, out);
	}

	@Override
	public void revert() throws Exception {
		assert IN_FILE.length() == OUT_FILE.length();
		Files.delete(Paths.get(OUT_FILE));
	}

	@Override
	public boolean isRevertable() {
		return true;
	}
}
