import java.util.List;

/**
 * This class has an static method to pack the string and send it through the
 * socket
 * 
 * @version JAN 16 2011
 * @author David Azocar
 */
public class Packer {

	public static String getResponse(String task, List<String> response) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(task + ":");

		for (int i = 0; i < response.size() - 1; i++) {
			stringBuilder.append(response.get(i) + " ");
		}
		stringBuilder.append(response.get(response.size() - 1));

		return stringBuilder.toString();
	}

}
