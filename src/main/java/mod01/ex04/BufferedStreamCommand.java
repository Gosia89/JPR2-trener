package mod01.ex04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import mod01.ex04.utils.Command;
import mod01.ex04.utils.FileGenerator;

import static mod01.ex04.utils.FileGenerator.*;


public class BufferedStreamCommand implements Command {

	@Override
	public void execute() throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				IN_FILE));
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(OUT_FILE));

		// +1 for \n
		byte[] buffer = new byte[FileGenerator.LINE_SIZE + 1];
		int c;
		while ((c = in.read(buffer)) > 0) {
			out.write(buffer, 0, c);
		}
		in.close();
		out.close();
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
