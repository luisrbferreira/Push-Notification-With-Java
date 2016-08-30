package br.com.pushnotification;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EnviarNotificacao {
	
	public EnviarNotificacao(){
		
	}
	
	public void send(){
		try {
	        HttpURLConnection httpcon = (HttpURLConnection) ((new URL("https://fcm.googleapis.com/fcm/send").openConnection()));
	        httpcon.setDoOutput(true);
	        httpcon.setRequestProperty("Content-Type", "application/json");
	        httpcon.setRequestProperty("Authorization", "key=SuaKeyAqui");
	        httpcon.setRequestMethod("POST");
	        httpcon.connect();
	        System.out.println("Connected!");

	        byte[] outputBytes = "{\"notification\":{\"title\": \"My title\", \"body\": \"My text\"}, \"to\": \"/topics/ALL\"}".getBytes("UTF-8");
	        java.io.OutputStream os = httpcon.getOutputStream();
	        os.write(outputBytes);
	        os.close();

	        // Reading response
	        java.io.InputStream input = httpcon.getInputStream();
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
	            for (String line; (line = reader.readLine()) != null;) {
	                System.out.println(line);
	            }
	        }

	        System.out.println("Http POST request sent!");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}