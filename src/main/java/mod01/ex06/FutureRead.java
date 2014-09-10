package mod01.ex06;

import static java.nio.channels.AsynchronousFileChannel.open;
import static java.nio.file.StandardOpenOption.READ;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

public class FutureRead {

	public static String readText(int bufferSize, Path path) {
		System.out.println("Preparing to read text from file: "
				+ path.getFileName());
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
		int bytesRead = 0;
		String content = "";
		Future<Integer> result = null;
		try (AsynchronousFileChannel asyncChannel = open(path, READ)) {
			result = asyncChannel.read(buffer, 0);

			doSomethingElse(result);

			System.out.println("The result is available!");
			bytesRead = result.get();
			System.out.println("Read bytes: " + bytesRead);
			buffer.flip();
			content = Charset.defaultCharset().decode(buffer).toString();
		} catch (Exception e) {
			System.err.println(e);
		}
		return content;
	}
	
	public static void doSomethingElse(Future<?> result) {
		int sheep = 0;
		while (!result.isDone()) {
			System.out.println("Counting sheep... " + ++sheep);
		}
	}

	public static void main(String[] args) {
		Path path = Paths.get("Placowka.txt");
		readText(500_000, path);
	}
}