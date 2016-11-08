/****************
 * Andres Aguayo
 * CODE2040
 * Step 2
 ****************/

import java.net.*;
import java.io.*;
import org.json.*;

public class APIreverse {

	public final static void main(String[] args) {
		
		//set target url
		String target = "http://challenge.code2040.org/api/reverse";
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
			
			//create JSON object
			JSONObject obj = new JSONObject();
			obj.put("token", "e8e73cbfeb50a85f4d013b3851cd7917");
			
			//pass JSON object to server
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(obj.toString());
			out.flush();
			out.close();
			
			//use BufferedReader to read response from server
			InputStream in = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			//take response as string
			String line = rd.readLine();
			rd.close();
			
			//split string into array of characters & initialize useful variables
			char[] chars = line.toCharArray();
			int last = chars.length-1;
			int mid = chars.length/2;
			char tmp;
			
			//iterate through half of the array, switching each character with
			//its mirror character in the other half
			for(int i=0; i<mid; i++) {
				
				tmp = chars[i];
				chars[i] = chars[last-i];
				chars[last-i] = tmp;
				
			}
			
			//convert character array to string
			String reverse = new String(chars);
			
			//set new target url
			target = "http://challenge.code2040.org/api/reverse/validate";
			
			try {
				
				//open connection and flag input/output for use
				url = new URL(target);
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
				dict.put("string", reverse);
				
				//pass JSON dictionary to server
				out = new DataOutputStream(connection.getOutputStream());
				out.writeBytes(dict.toString());
				out.flush();
				out.close();
				
				//use BufferedReader to read response from server
				in = connection.getInputStream();
				rd = new BufferedReader(new InputStreamReader(in));
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
			
		} catch (Exception e) {
			
			e.printStackTrace(); //catch error and print to standard error
			
		} finally {
			
			connection.disconnect();
			
		}
		
		
	}
	
}
