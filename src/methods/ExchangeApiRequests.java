package methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class ExchangeApiRequests {

	private final static String apiKey = "KXNv8ZdjZ4WoBZXjo3Hr3H6YDD0nfNXc";

	private static StringBuffer sendHttpGETRequest(String typeOfRequest, String apiKey) throws IOException {

		// Construct the API endpoint URL with the query parameters
		String GET_URL = "https://api.apilayer.com/exchangerates_data/" + typeOfRequest;

		// Open a connection to the API endpoint
		URL url = new URL(GET_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");

		// Add the apikey header with the API key
		httpURLConnection.setRequestProperty("apikey", apiKey);

		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			// Read the response from the API endpoint
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response;
		}
		return null;
	}

	public static double getExchangedCurrencyRequest(String from, String to, Double amount) {

		StringBuffer response;
		try {
			response = sendHttpGETRequest("convert?to=" + to + "&from=" + from + "&amount=" + amount, apiKey);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response = null;
		}

		if (response != null) {

			try {
				JSONObject obj = new JSONObject(response.toString());
				double exchange = obj.getDouble("result");
				return exchange;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return 0.0;
	}

	public static List<String[]> getsymbolRequest() {

		StringBuffer response;
		try {
			response = sendHttpGETRequest("symbols", apiKey);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response = null;
		}

		if (response != null) {

			try {
				JSONObject obj = new JSONObject(response.toString());
				JSONObject symbols = obj.getJSONObject("symbols");
				
				// Extract the symbol keys and descriptions
		        ArrayList<String> keysList = new ArrayList<>(symbols.keySet());
		        Collections.sort(keysList);

		        ArrayList<String> descList = new ArrayList<>();
		        for (String key : keysList) {
		            String desc = key + " - " + symbols.getString(key);
		            descList.add(desc);
		        }

		        // Convert the ArrayLists to String[]
		        String[] symbolKeys = keysList.toArray(new String[0]);
		        String[] descriptionArray = descList.toArray(new String[0]);		

				List<String[]> stringList = new ArrayList<String[]>();
				stringList.add(symbolKeys);
				stringList.add(descriptionArray);		

				/*//If i want the values, this in how
				String[] values = new String[symbols.length()];	
				i = 0;
				for (String key : symbols.keySet()) {
					values[i++] = symbols.getString(key);
				}*/
				return stringList;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

}
