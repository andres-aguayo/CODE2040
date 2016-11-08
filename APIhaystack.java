/****************
 * Andres Aguayo
 * CODE2040
 * Step 3
 ****************/

import java.net.*;
import java.io.*;
import org.json.*;

public class APIhaystack {

	
public final static void main(String[] args) {
		
		//set target url
		String target = "http://challenge.code2040.org/api/haystack";
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
			
			//create JSONObject
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
			String line;
			StringBuffer response = new StringBuffer();
			
			//take response as string
			while((line = rd.readLine()) != null) {
				response.append(line);
			}

			//cast input string into a JSON dictionary
			JSONObject inDict = new JSONObject(response.toString());
			
			//take needle string and haystack array from JSON dictionary
			String needle = inDict.getString("needle");
			JSONArray haystack = inDict.getJSONArray("haystack");
			String check;
			int index = 0;
			
			//iterate through the haystack and compare each string with needle
			for(int i=0; i<haystack.length(); i++) {
				
				check = haystack.getString(i);
				
				//when needle is found, save the index and break loop
				if(check.equals(needle)) {
					index = i;
					break;
				}
				
			}
			
			rd.close();
			
			//set new target url
			target = "http://challenge.code2040.org/api/haystack/validate";
			
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
				
				//create output dictionary
				JSONObject outDict = new JSONObject();
				outDict.put("token", "e8e73cbfeb50a85f4d013b3851cd7917");
				outDict.put("needle", index);
				
				//pass output dictionary to server
				out = new DataOutputStream(connection.getOutputStream());
				out.writeBytes(outDict.toString());
				out.flush();
				out.close();
				
				//use BufferedReader to read response from server
				in = connection.getInputStream();
				rd = new BufferedReader(new InputStreamReader(in));
				response = new StringBuffer();
				
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
