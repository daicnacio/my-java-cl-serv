public class FirstProtocol {
	private static final int WAITING = 0;
	private static final int REQUESTTASK = 1;
	private int state = WAITING;
	private String[] tasks = { "Addition", "Sustraction", "Multiplication",
			"Division" };
	private TaskProcessor processor = new TaskProcessor();

	/*
	 * The possible inputs from client are:
	 * "operation:parameter1 parameter2 ... parametern" "try again"
	 * 
	 * The possible outputs to client are: "Waiting for the task."
	 * "output:parameter" "no task"
	 */
	public String processInput(String theInput) {
		System.out.println("server:" + theInput);
		String theOutput = null;

		if (state == WAITING) {
			theOutput = "Waiting for the task.";
			state = REQUESTTASK;

		} else if (state == REQUESTTASK) {
			Boolean find = false;
			// try again case
			if (theInput.equalsIgnoreCase("try again")) {
				theOutput = "Waiting for the task.";
				state = REQUESTTASK;
			}

			// task case
			else {
				Parser request = new Parser(theInput);

				// look for the task in the tasks list
				for (int i = 0; i < tasks.length; i++) {
					if (request.getTask().equalsIgnoreCase(tasks[i])) {
						// Do the task, in this case theOutput has the format output:parameter
						theOutput = processor.processTask(request);
						state = REQUESTTASK;
						find = true;
					}
				}
				if (!find) {
					theOutput = "no task";
					state = REQUESTTASK;
				}
			}

		}
		return theOutput;
	}
}
