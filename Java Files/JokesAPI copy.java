
import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JokesAPI {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		getJokes(input);
	}

	public static void getJokes(Scanner input) throws IOException {
		System.out.println("We will now retrieve a joke for you");

		String website = "https://official-joke-api.appspot.com/random_joke";

		String jsonArray = httpGetJokes(website);

		// System.out.println(jsonArray);

		OfficialJokesObject jokeObject = parseJson(jsonArray);

		System.out.println(jokeObject.setup + " Press Enter to see the answer");

		System.in.read();

		System.out.println(jokeObject.punchline);

	}

	public static OfficialJokesObject parseJson(String json) {

		// Parse the JSON data into weatherObject
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);
		OfficialJokesObject jokeObject = gson.fromJson(reader, OfficialJokesObject.class);

		return jokeObject;
	}

	public static String httpGetJokes(String website) throws IOException {

		System.out.println(website);

		// Create the request
		URL url = new URL(website);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json"); // add header to a request

		// Configuring Timeouts (5 seconds)
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);

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
