package steam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient; // < New in Java 11, can also handle HTTP/2 requests!
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/*
Send HTTP requests using the new API
(Java 11 and above)
This example uses the new java.net.HttpClient which handles
asynchronous operations automatically.
 */
public class SteamProfil {

	public static boolean getProfil(String steamid) {

		String url = "https://portfolio-gaetan.fr/api/steamProfil/" + steamid;

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		try {
			client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body)
					.thenApply(SteamProfil::parse).join();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}
		return false;
	}

	public static String parse(String responseBody) {
		JSONArray steamProfils = new JSONArray(responseBody);
		System.out.println(steamProfils.length());

		if (steamProfils.length() != 0) {

			try {
				for (int i = 0; i < steamProfils.length(); i++) {

					try {
						Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
								+ "Récupération des profils steam" + " \n");
					} catch (IOException t) {
						// TODO Auto-generated catch block
						t.printStackTrace();
					}

					JSONObject profil = steamProfils.getJSONObject(i);

					String personaname = profil.getString("personaname");
					String avatar = profil.getString("avatar");
					String steamid = profil.getString("steamid");
					String loccountrycode = profil.getString("loccountrycode");
					String name = profil.getString("realname");
					
					App.newPseudoProfil = personaname;
					App.newAvatarProfil = avatar;
					App.steamidProfil = steamid;
					App.loccountrycodeProfil = loccountrycode;
					App.nameProfil = name;

				}
			} catch (Exception e) {
				try {
					Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
				} catch (IOException t) {
					// TODO Auto-generated catch block
					t.printStackTrace();
				}
			}

		} else {
			App.newPseudoProfil = null;
		}

		return null;
	}

}