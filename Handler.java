import java.util.LinkedList;

/**
 * This class process the requests.
 */
public class Handler implements Runnable {
	LinkedList<String> sharedQueue;

	public Handler(LinkedList<String> taskQueue) {
		// TODO Auto-generated constructor stub
		sharedQueue = taskQueue;
	}

	@Override
	public void run() {
		/*
		 * for now, the handler takes the string from the queue and print it in
		 * the system output.
		 */
		boolean working = true;
		while (working) {
			/*
			 * this part must be synchronized
			 */
			String r = sharedQueue.getFirst();
			System.out.println(r);
			if (r.equalsIgnoreCase("stop")) {
				System.out.println("stopping handler.");
				working = false;
			}

		}

	}

}
