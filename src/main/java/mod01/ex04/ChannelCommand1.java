package mod01.ex04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import mod01.ex04.utils.Command;

import static mod01.ex04.utils.FileGenerator.*;

public class ChannelCommand1 implements Command {

	@Override
	public void execute() throws IOException {
		FileInputStream fis = new FileInputStream(IN_FILE);
		FileChannel in = fis.getChannel();
		FileOutputStream fos = new FileOutputStream(OUT_FILE);
		FileChannel out = fos.getChannel();

		 ByteBuffer buffer = ByteBuffer.allocate(LINE_SIZE + 1);
		 while (in.read(buffer) > 0) {
		 buffer.flip();
		 out.write(buffer);
		 buffer.clear();
		 }
		in.close();
		fis.close();
		out.close();
		fos.close();
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
