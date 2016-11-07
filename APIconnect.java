/****************
 * Andres Aguayo
 * CODE2040
 ****************/

import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;

public class APIconnect {

	public final static void main(String[] args) {
		
		String target = "http://challenge.code2040.org/api/register";
		HttpURLConnection connection = null;
		
		try {
		
			URL url = new URL(target);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			
			JSONObject obj = new JSONObject();
		
			obj.put("token", "e8e73cbfeb50a85f4d013b3851cd7917");
			obj.put("github", "https://github.com/andres-aguayo/CODE2040");
			
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(obj.toString());
			out.flush();
			out.close();
			
			InputStream in = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			String line;
			StringBuffer response = new StringBuffer();
			
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			
			rd.close();
			System.out.print(response.toString());
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			connection.disconnect();
			
		}
		
	}
	
}
