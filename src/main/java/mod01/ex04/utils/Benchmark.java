package mod01.ex04.utils;

public class Benchmark {

	public static void execute(String id, Command command, int repeats)
			throws Exception {
		// to avoid checking 'isRevertable' each time or execute dummy methods
		if (command.isRevertable()) {
			executeRevertable(id, command, repeats);
		} else {
			executeNotRevertable(id, command, repeats);
		}
	}

	private static void executeNotRevertable(String id, Command command,
			int repeats) throws Exception {
		Stopper stopper = new Stopper();

		stopper.start(id, repeats);
		for (int i = 0; i < repeats; i++) {
			command.execute();
		}
		stopper.stop(true);
	}

	private static void executeRevertable(String id, Command command,
			int repeats) throws Exception {
		Stopper stopper = new Stopper();
		stopper.start(id, repeats);
		long duration = 0;

		for (int i = 0; i < repeats; i++) {
			// count only execution time
			stopper.start();
			command.execute();
			duration += stopper.stop();
			command.revert();
		}
		stopper.stop(duration);
	}
}
