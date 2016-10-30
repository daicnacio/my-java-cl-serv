

import java.util.LinkedList;

/**
 * This is the server who launch both the connection server and the handler.
 */
public class SharedQueueServer {

	private static LinkedList<String> taskQueue;

	public static void main(String[] args) {
		/*
		 * Shared Queue
		 */
		taskQueue = new LinkedList<String>();
		/*
		 * ConnectionServer is the thread who receives the requests and put it
		 * on the queue
		 */
		ConnectionServer server = new ConnectionServer(taskQueue);
		Handler handler = new Handler(taskQueue);
		/*
		 * To do this, both classes must implement Runnable
		 */
		Thread sThread = new Thread(server);
		Thread hThread = new Thread(handler);
		sThread.start();
		hThread.start();

	}

}
