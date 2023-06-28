package steam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	static String mysqlHote;
	static String mysqlPort;
	static String mysqlDb;
	static String mysqlUsername;
	static String mysqlPassword;

	public static void login() {

		try {

			mysqlPassword = EncryptPass
					.decrypt("9MmyxyzUn6CdCBDlItnkMQ==:tBpr4wf6K8Dp2XIHp/UXnZzBZhf0dbAliYhhiNuakm8=");
			mysqlUsername = EncryptPass.decrypt("tE9ZRLZpaaANPSbvDlbRaw==:zJDJQZEM53FJoicmX//e8Q==");
			mysqlHote = EncryptPass.decrypt("quZ9oJQR4r8wxu0xM3EqmQ==:3IcSV7SzMJvJKq5SSXTEmgfygwIQrDA+u8sNKzBEmw8=");
			mysqlPort = EncryptPass.decrypt("fBSpgwTYxSjBOcxyKwxAwA==:lqEuJcqj3fcssxcpEAWCDA==");
			mysqlDb = EncryptPass.decrypt("VCtY1cbUklnuirt2y2R2Kw==:Xsv5+g/5Nu63cURmx8/LLg==");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + mysqlHote + ":" + mysqlPort + "/" + mysqlDb,
					mysqlUsername, mysqlPassword);
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
						+ "Connexion à la base de donnée réussi" + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			App.login(conn);

			conn.close();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	public static void profils() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + mysqlHote + ":" + mysqlPort + "/" + mysqlDb,
					mysqlUsername, mysqlPassword);
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
						+ "Connexion à la base de donnée réussi" + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			App.profils(conn);

			conn.close();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	public static void addProfils() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + mysqlHote + ":" + mysqlPort + "/" + mysqlDb,
					mysqlUsername, mysqlPassword);
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
						+ "Connexion à la base de donnée réussi" + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			App.addProfil(conn);

			conn.close();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	public static void removeProfils() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + mysqlHote + ":" + mysqlPort + "/" + mysqlDb,
					mysqlUsername, mysqlPassword);
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
						+ "Connexion à la base de donnée réussi" + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			App.removeProfil(conn);

			conn.close();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	public static void createAccounts() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + mysqlHote + ":" + mysqlPort + "/" + mysqlDb,
					mysqlUsername, mysqlPassword);
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
						+ "Connexion à la base de donnée réussi" + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}

			App.createAccount(conn);

			conn.close();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

}
