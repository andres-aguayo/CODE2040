/****************
 * Andres Aguayo
 * CODE2040
 * Step 4
 ****************/

import java.net.*;
import java.io.*;
import org.json.*;
import java.util.*;


public class APIprefix {
	
	
public final static void main(String[] args) {
		
		String target = "http://challenge.code2040.org/api/prefix";
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
			}

			JSONObject dict = new JSONObject(response.toString());
			String prefix = dict.getString("prefix");
			
			JSONArray arr = dict.getJSONArray("array");
			String check;
			LinkedList<String> list = new LinkedList<String>();
			
			for(int i=0; i<arr.length(); i++) {
				
				check = arr.getString(i);
				
				for(int j=0; j<prefix.length(); j++) {
					
					if(check.charAt(j) != prefix.charAt(j)) {
						list.add(check);
						break;
					}
					
				}
				
			}
			
			rd.close();
			
			target = "http://challenge.code2040.org/api/prefix/validate";
			
			try {
				
				url = new URL(target);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.connect();
				
				obj = new JSONObject();
			
				obj.put("token", "e8e73cbfeb50a85f4d013b3851cd7917");
				
				JSONArray array = new JSONArray();
				
				ListIterator<String> it = list.listIterator();
				while(it.hasNext()) {
					array.put(it.next());
				}
				
				obj.put("array", array);
				
				out = new DataOutputStream(connection.getOutputStream());
				out.writeBytes(obj.toString());
				out.flush();
				out.close();
				
				in = connection.getInputStream();
				rd = new BufferedReader(new InputStreamReader(in));
				response = new StringBuffer();
				
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
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			connection.disconnect();
			
		}
		
	}

}
