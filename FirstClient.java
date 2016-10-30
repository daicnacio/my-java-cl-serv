import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Client
 * 
 * @author David Azocar
 * 
 */
public class FirstClient {

	public static void main(String[] args) {
		Socket firstSocket = null;
		// TODO Read about PrintWriter, BufferedReader
		PrintWriter out = null;
		BufferedReader in = null;
		int serverPort = 4444;
		String host = "localhost";

		/*
		 * Connection
		 */
		try {
			System.out.println("Connecting to server: " + host + ", port:"
					+ serverPort + ".");
			firstSocket = new Socket(host, serverPort);
			System.out.println("done.");
			// read about function getOutputStream,getInputSream
			out = new PrintWriter(firstSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					firstSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + host + ".");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "
					+ host + ".");
			System.exit(1);
		}

		/*
		 * talking
		 */
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String fromServer;
		String fromUser;
		// The server is the first to talk?
		try {
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				/*
				 * talking between the user and the client
				 */
				fromUser = getTaskString(fromServer, stdIn);
				// only the client can stop the connection
				if (fromUser.equals("stop"))
					break;
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}

			/*
			 * closing the connection
			 */

			out.close();
			in.close();
			stdIn.close();
			firstSocket.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
	}

	public static String getTaskString(String fromserver, BufferedReader stdIn) {
		/*
		 * The possible inputs from server are: "Waiting for the task."
		 * "output:parameter" "no task"
		 * 
		 * The possible outputs to send to the server are:
		 * "operation:parameter1 parameter2 ... parametern" "try again"
		 * 
		 * The possible outputs used in the same client are: "stop"
		 */
		
		if (fromserver.equalsIgnoreCase("Waiting for the task.")) {
			// get the task from the user
			System.out
					.println("Client:Please enter the name of the task, or write stop.");
			try {
				String input = stdIn.readLine();
				if (input.equalsIgnoreCase("stop")) {
					return "stop";
				}
				System.out
						.println("Client:Please enter the parameters one by one and press enter");
				List<String> parameters = new ArrayList<String>();
				for (int times = 0; times < 2; times++) {
					parameters.add(stdIn.readLine());
				}

				return Packer.getResponse(input, parameters);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// task requested is not available
		if (fromserver.equalsIgnoreCase("no task")) {
			try {
				Boolean response = false;
				while (!response) {

					System.out.println("Task is not available.Try again?(y/n)");
					String input = stdIn.readLine();
					if (input.equalsIgnoreCase("y")) {
						return "try again";

					}
					if (input.equalsIgnoreCase("n")) {
						return "stop";
					}
					System.out.println("incorrect answer.");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// default case:results
		Parser results = new Parser(fromserver);
		System.out.println("result:");
		for (int i = 0; i < results.numberOfParameters(); i++) {
			System.out.println(results.getParameter(i));
		}
		return "try again";
	}
}
