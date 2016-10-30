import java.net.*;
import java.io.*;

public class MultipleServer {
	private static int port = 4444;

	public static void main(String[] args) {
		ServerSocket s = null;
		Socket clientSocket = null;
		// Create Socket and Bind
		try {
			s = new ServerSocket(port);
			System.out.println("Main Server started.");
			// TODO check how to print the thread name
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

		// Start to listening
		// TODO implement server stop from console
		while (true) {
			try {
				clientSocket = s.accept();
				System.out.println("Connection request received.");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			// once the connection is established, launch the Thread
			launchThread(clientSocket);// TODO implement try catch for launchThread function
			System.out.println("Thread launch Ok.");
		}

	}
// TODO should launchThread throws an exception in case it is not possible to run the thread?
	private static void launchThread(Socket clientSocket) {
		FirstServer serverThread = new FirstServer(clientSocket);
		Thread sThread = new Thread(serverThread);
		sThread.start();
	}

}
