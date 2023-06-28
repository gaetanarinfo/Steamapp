package steam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;

public class Logs {

	static Date DateDuJour = new Date();
	static DateFormat datefl = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);

	// Class permettant de sauvegarder les logs
	public static String SaveTextLogs(String ContentText) throws IOException {

		FileWriter fileWriter = new FileWriter("Logs.txt");
		fileWriter.write(ContentText);

		fileWriter.close();

		return ContentText;
	}

	public static String ReadTextLogs() throws IOException {

		try {
			File myObj = new File("Logs.txt");

			if (myObj.createNewFile()) {

			} else {

			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		var fileName = "Logs.txt";
		var filePath = Paths.get(fileName);

		byte[] data;

		data = Files.readAllBytes(filePath);
		var content = new String(data);

		System.out.println(content);

		return content;

	}

	public static File CheckEmptyFileLogs() {

		File file = new File("Logs.txt");

		return file;
	}

	public final static String resultLogs = "";

	public static void LogsDebug(String ContentTextLogs) throws IOException {

		SaveTextLogs(resultLogs.toString());

	}

}
