package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpCommand {
	
	ArrayList<String> commandList = new ArrayList<String>();
	
	public void pushHttpCommand(String url) {
		commandList.add(url);
	}
	
	public void sendCommand() {
		for(String urlString : commandList) {
			try {
				URL url = new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
