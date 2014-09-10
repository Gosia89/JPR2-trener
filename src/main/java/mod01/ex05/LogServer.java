package mod01.ex05;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServer {

	public static void main(String[] args) {
		final int PORT = 5280;
		final String LOG_FILE = "distlog.txt";

		System.out.println("Serwer uruchomiony. Nasluch na porcie 5280...");
		try (
				ServerSocket serverSocket = new ServerSocket(PORT);
				Socket socket = serverSocket.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE));) {
			String info;
			while ((info = in.readLine()) != null) {
				out.println(info);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}