public class TaskProcessor {
	public TaskProcessor() {
	}

	/*
	 * this function always returns a String with the format "output:number"
	 */
	public String processTask(Parser task) {
		// TODO implement processTask method
		if (task == null) {
			return "output:0";
		} else if (task.getTask().equalsIgnoreCase("addition")) {
			return addition(task);
		} else if (task.getTask().equalsIgnoreCase("sustraction")) {
			return sustraction(task);
		} else if (task.getTask().equalsIgnoreCase("multiplication")) {
			return multiplication(task);
		} else if (task.getTask().equalsIgnoreCase("division")) {
			return division(task);
		} else {
			return "output:0";

		}

	}

	private String multiplication(Parser input) {
		System.out.println("multiplication done.");
		return "multiplication:" + (input.getParameter(0) * input.getParameter(1));
	}
	private String division(Parser input) {
		System.out.println("divisio done.");
		return "division:" + (input.getParameter(0) / input.getParameter(1));
	}
	private String addition(Parser input) {
		System.out.println("addition done.");
		return "addition:" + (input.getParameter(0) + input.getParameter(1));
	}

	private String sustraction(Parser input) {
		System.out.println("sustraction done.");
		return "sustraction:" + (input.getParameter(0) - input.getParameter(1));
	}
}
