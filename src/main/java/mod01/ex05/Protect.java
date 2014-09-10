package mod01.ex05;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.SocketHandler;

public class Protect {
	private static Logger netLogger;

	private static final String HOST = "localhost";
	private static final int PORT = 5280;

	public static void main(String[] args) {
		setupLogger();
		protectDir("src");
	}

	public static void protectDir(String dir) {
		WatchService watcher = null;
		try {
			watcher = FileSystems.getDefault().newWatchService();
			Path path = FileSystems.getDefault().getPath(dir);
			path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		while (true) {
			try {
				WatchKey key = watcher.take();
				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					Path filename = (Path) watchEvent.context();
					WatchEvent.Kind<?> kind = watchEvent.kind();
					// System.out.println(kind + ": " + filename);
					netLogger.warning(kind.toString() + ": " + filename);
				}
				// check if key is correct
				// key may be wrong when watched dir is removed
				if (!key.reset()) {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	private static void setupLogger() {
		// unregister no-name logger
		Logger rootLogger = Logger.getLogger("");
		Handler[] hs = rootLogger.getHandlers();
		for (Handler h : hs) {
			rootLogger.removeHandler(h);
		}

		// configure socket logger
		netLogger = Logger.getLogger("pl.altkom.netlog");
		netLogger.setLevel(Level.INFO);
		Handler handler = null;
		try {
			handler = new SocketHandler(HOST, PORT);
		} catch (IOException e) {
			System.out.println("SocketHandler exception: " + e.getMessage());
			System.exit(-1);
		}
		handler.setFormatter(new SimpleFormatter());
		netLogger.addHandler(handler);
	}

}
