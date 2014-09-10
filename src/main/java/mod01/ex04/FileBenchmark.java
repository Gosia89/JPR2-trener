package mod01.ex04;

import mod01.ex04.utils.Benchmark;
import mod01.ex04.utils.Stopper;

public class FileBenchmark {
	private static final int REPEATS = 10;
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Warming up...");
		Stopper.header(true);
		Benchmark.execute("BufferedStream", new BufferedStreamCommand(), REPEATS);

		System.out.println("\nReal execution...");
		Stopper.header(true);
		execution();
	}

	private static void execution() throws Exception {
		Benchmark.execute("BufferedStream", new BufferedStreamCommand(), REPEATS);
		Benchmark.execute("Channel (buffer)", new ChannelCommand1(), REPEATS);
		Benchmark.execute("Channel (transfer)", new ChannelCommand2(), REPEATS);
		Benchmark.execute("NIO.2 copy", new PathCommand(), REPEATS);
	}
}

