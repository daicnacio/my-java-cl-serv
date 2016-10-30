import java.util.ArrayList;
import java.util.List;

/**
 * This class parses the string sent between client and server, and vice versa.
 * 
 * @author David Azocar
 * @version JAN 16 2011
 * 
 */
public class Parser {
	/*
	 * Task stores the operator. It can be 'answer' when the string is sent from
	 * server to client. the format of the string must be: 'operator:parameter1
	 * parameter 2 .... parametern'
	 */
	private String task;
	/*
	 * The number of arguments is variable and they are stored as string.
	 */
	private List<String> arguments = new ArrayList<String>();

	/*
	 * constructor
	 */
	public Parser(String message) {
		parseMessage(message);
	}

	/*
	 * method parseMessage stores the operator and the parameters in the object.
	 */
	public void parseMessage(String message) {
		String[] messageArray = message.split(":");
		// TODO throws and exception in case the ':' character is not present
		task = messageArray[0];

		String[] argumentsArray = messageArray[1].split(" ");
		for (String string : argumentsArray) {
			arguments.add(string);
		}
	}

	public String getTask() {
		//TODO throws an exception in case the element does not exist
			return task;
	}
	public double getParameter(int i){
		// TODO throws an exception in case the element does not exist
		return Double.parseDouble(arguments.get(i));
	}
	public int numberOfParameters(){
		return arguments.size();
	}

}
