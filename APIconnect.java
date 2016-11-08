/****************
 * Andres Aguayo
 * CODE2040
 * Step 1
 ****************/

import java.net.*;
import java.io.*;
import org.json.*;

public class APIconnect {

	public final static void main(String[] args) {
		
		//set target url
		String target = "http://challenge.code2040.org/api/register";
		HttpURLConnection connection = null;
		
		try {
		
			//open connection and flag input/output for use
			URL url = new URL(target);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//inform server of JSON data type
			connection.setRequestProperty("Content-Type", "application/json");
			connection.connect();
			
			//create dictionary
			JSONObject dict = new JSONObject();
			dict.put("token", "e8e73cbfeb50a85f4d013b3851cd7917");
			dict.put("github", "https://github.com/andres-aguayo/CODE2040");
			
			//pass JSON dictionary to server
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(dict.toString());
			out.flush();
			out.close();
			
			//use BufferedReader to read response from server
			InputStream in = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			String line;
			StringBuffer response = new StringBuffer();
			
			//take response as string
			while((line = rd.readLine()) != null) {
				response.append(line);
			}
			
			rd.close();
			System.out.print(response.toString());
		
		} catch (Exception e) {
			
			e.printStackTrace(); //catch error and print to standard error
			
		} finally {
			
			connection.disconnect(); 
			
		}
		
	}
	
}
