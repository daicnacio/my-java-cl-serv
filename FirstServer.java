import java.net.*;
import java.io.*;

public class FirstServer implements Runnable {

	private Socket clientSocket;

	public FirstServer() {
		clientSocket = null;
	}

	public FirstServer(Socket s) {
		clientSocket = s;
	}

	@Override
	public void run() {

		PrintWriter out;
		try {
			// Does BufferedWriter exist?
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String inputLine, outputLine;
			FirstProtocol fprotocol = new FirstProtocol();
			// implement printing the name of the thread
			System.out.println("Thread started.");
			outputLine = fprotocol.processInput(null);
			// the first response from the server: "waiting for the task."
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = fprotocol.processInput(inputLine);
				//possible request of stop server from client
				if (outputLine.equals("stop"))
					break;
				out.println(outputLine);
			}
			out.close();
			in.close();
			clientSocket.close();
			System.out.println("Thread connection closed.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
