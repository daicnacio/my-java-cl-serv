import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * This Class listen to the connections and put it on the Queue
 */
public class ConnectionServer implements Runnable {
	LinkedList<String> sharedQueue;
	private static int port = 4444;
	
	public ConnectionServer(LinkedList<String> taskQueue) {
		sharedQueue = taskQueue;
	}

	@Override
	public void run() {
		ServerSocket s = null;
		Socket clientSocket = null;
		// Create Socket and Bind
		try {
			s = new ServerSocket(port);
			System.out.println("Connection Server started.");
			// TODO check how to print the thread name
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
		Boolean listening = true;
		// Start to listening
		try {
			while (listening) {

				clientSocket = s.accept();
				System.out.println("Connection request received.");

				// once the connection is established, put the request on the
				// queue
				listening = taskOnQueue(clientSocket);
				clientSocket.close();
			}
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This Method receives the connection and put it on the queue. if the
	 * client send a close connection request, then this function returns false
	 * so as to break the loop.
	 * 
	 * @param clientSocket
	 * @return true, unless the client requests to stop the connection, then false
	 */
	private Boolean taskOnQueue(Socket clientSocket) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out;
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			String inputLine, outputLine;
			/*
			 * this is one step
			 */
			outputLine="connection established.";
			out.println(outputLine);

			inputLine = in.readLine();
			/*
			 * this should be synchronized
			 */
			sharedQueue.addLast(inputLine);
			
			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread connection closed.");
		/*
		 * just one step for now
		 */
		return false;
		

	}

}
