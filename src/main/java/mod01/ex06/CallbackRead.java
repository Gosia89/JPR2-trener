package mod01.ex06;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import static java.nio.channels.AsynchronousFileChannel.*;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.READ;

public class CallbackRead {
	private static String content = "";
	private static int bytesRead;

	public static String readText(int bufferSize, Path path) {
		final Thread current = Thread.currentThread();
	
		CompletionHandler<Integer, ByteBuffer> handler = new CompletionHandler<Integer, ByteBuffer>() {

			// the buffer was passed as attachment
			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				buffer.flip();
				content = Charset.defaultCharset().decode(buffer).toString();
				bytesRead = result;
				current.interrupt();
			}

			@Override
			public void failed(Throwable e, ByteBuffer attachment) {
				System.err.println("Error:" + e.toString());
				current.interrupt();
			}
		};
		try (AsynchronousFileChannel asynChannel = open(path, READ)) {
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			asynChannel.read(buffer, 0, buffer, handler);
			doSomethingElse();
			System.out.println("Read bytes: " + bytesRead);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return content;
	}

	public static void doSomethingElse() {
		int sheep = 0;
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("Counting sheep... " + ++sheep);
		}
	}

	public static void main(String[] args) {
		Path path = Paths.get("Placowka.txt");
		readText(500_000, path);
	}
}