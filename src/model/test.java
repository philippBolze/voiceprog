package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

	public static void main(String[] args) {

		try {
		      URL url = new URL("http://192.168.178.33:8083/fhem?cmd.licht_schuppen=set%20licht_schuppen%20on");
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("GET");
		      conn.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
