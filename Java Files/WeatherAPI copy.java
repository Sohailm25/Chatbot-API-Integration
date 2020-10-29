import java.util.*;
import java.io.*; // for IOException
import java.net.*; // for HttpGet Functionality

import com.google.gson.*; // import GSON into project

public class WeatherAPI {

	public static void getWeather(Scanner input) throws IOException {
		final String WEATHER_API_KEY = "0df8307658eec41e5de62eb2fb7caaad";

		// Ask user for name of a city
		System.out.println("Please enter a City");
		String city = input.next();

		// Ask user for Zip Code and check length
		int zip = 00000;
		String country = "us";
		String zipCheck;
		do {
			System.out.println("Please enter a 5 digit zip code");
			zip = input.nextInt();

			zipCheck = String.valueOf(zip);

		} while (zipCheck.length() != 5);
		String website = "";
		// API link
		if (zip != 00000)
			website = "http://api.openweathermap.org/data/2.5/weather?zip=" + zip + "," + country + "&appid="
					+ WEATHER_API_KEY;
		// else
		website = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + WEATHER_API_KEY;

		// Perform HTTP Request & store the JSON array
		String jsonArray = httpGetWeather(website);

		// Parse the JSON & Create a weatherObject (that contains all the JSON elements)
		OpenWeather weatherObject = parseJson(jsonArray);

		double temp = ((((weatherObject.main.getTemp() - 273.15) * 9.0) / 5.0) + 32);
		double high = ((weatherObject.main.getTemp_max() - 273.15) * (9 / 5.0) + 32);
		double low = ((weatherObject.main.getTemp_min() - 273.15) * (9 / 5.0) + 32);

		System.out.printf("The Temperature right now is: %.2f", temp);
		System.out.println("° Fahrenheit");
		System.out.printf("The High today will be: %.2f", high);
		System.out.println("° Fahrenheit");
		System.out.printf("The Low today will be: %.2f", low);
		System.out.println("° Fahrenheit");

	}

	public static OpenWeather parseJson(String json) {
		// Create a parser object, and convert the jsonArray to a jsonObject
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject obj = element.getAsJsonObject();

		// Parse the JSON data into weatherObject
		Gson gson = new Gson();
		OpenWeather weatherObject = gson.fromJson(obj, OpenWeather.class);

		return weatherObject;
	}

	public static String httpGetWeather(String website) throws IOException {

		System.out.println(website);

		// Create the request
		URL url = new URL(website);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		// Request Parameters
		Map<String, String> parameters = new HashMap<>();
		parameters.put("param1", "val");

		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json"); // add header to a request
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(parameters)); // reference built Class
		out.flush();
		out.close();

		// Configuring Timeouts (5 seconds)
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

		// Handling Redirects
		HttpURLConnection.setFollowRedirects(false); // for all connections

		// Reading in the response
		int status = con.getResponseCode();

		// Reading the response on Failed Requests
		Reader streamReader = null;

		if (status > 299)
			streamReader = new InputStreamReader(con.getErrorStream());
		else {
			streamReader = new InputStreamReader(con.getInputStream());
		}

		BufferedReader in = new BufferedReader(streamReader);
		String inputLine;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();

		// Close the connection
		con.disconnect();

		// Returns the JSON
		return content.toString();

	}

}
