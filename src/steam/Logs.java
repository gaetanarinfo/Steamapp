package steam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	public static File CheckEmptyFileLogs() {

		File file = new File("Logs.txt");

		return file;
	}

}
