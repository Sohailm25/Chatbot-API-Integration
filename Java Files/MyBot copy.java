// ALL BOT FUNCTIONALITY SHOULD GO HERE

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jibble.pircbot.*;

public class MyBot extends PircBot {

	// constructor
	public MyBot() {
		this.setName("MyBot2");
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) {

		if (message.equalsIgnoreCase("sohailinfo")) {
			sendMessage(channel, "Welcome to Sohail's API. Please select an option below:");
			sendMessage(channel, "WEATHER: Get The Weather (SYNTAX: WEATHER frisco 75024)");
			sendMessage(channel, "JOKES: Display a Joke (SYNTAX: JOKES RANDOM");
		}

		// WEATHER API
		if (message.toLowerCase().startsWith("weather")) {
			final String WEATHER_API_KEY = "0df8307658eec41e5de62eb2fb7caaad";

			// split message into components
			String[] separatedMessage = message.split(" ");

			String city = separatedMessage[1].toLowerCase();
			String zipCheck = "00000";
			zipCheck = separatedMessage[2];

			String country = "us";
			String website = "";

			// exit if incorrect zip
			if (zipCheck.length() != 5) {
				sendMessage(channel, "Incorrect ZIP Code. Please try again.");
			}

			// call the API
			else {
				// create website link
				website = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCheck + "," + country + "&appid="
						+ WEATHER_API_KEY;
				// website = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
				// "&appid=" + WEATHER_API_KEY;

				// Perform HTTP Request & store the JSON array
				String jsonArray = "";
				try {
					jsonArray = WeatherAPI.httpGetWeather(website);
				} catch (IOException e) {
					sendMessage(channel, "API FAILED TO CALL: " + e);
				}

				// Parse the JSON & Create a OpenWeather object that contains all the JSON
				// elements
				OpenWeather weatherObject = WeatherAPI.parseJson(jsonArray);

				// set variables for the temp, high, and low
				double temp, high, low;

				// calculate FAHRENHEIT
				temp = ((((weatherObject.main.getTemp() - 273.15) * 9.0) / 5.0) + 32);
				high = ((weatherObject.main.getTemp_max() - 273.15) * (9 / 5.0) + 32);
				low = ((weatherObject.main.getTemp_min() - 273.15) * (9 / 5.0) + 32);

				// SET PRECISION OF DOUBLE
				temp = BigDecimal.valueOf(temp).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
				high = BigDecimal.valueOf(high).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
				low = BigDecimal.valueOf(low).setScale(1, RoundingMode.HALF_EVEN).doubleValue();

				sendMessage(channel, "The Temperature in " + city + " is " + temp + "° Fahrenheit");
				sendMessage(channel, "with a high of " + high + "° Fahrenheit");
				sendMessage(channel, "and a low of " + low + "° Fahrenheit");

			}

		}

		// SECOND API
		else if (message.toLowerCase().startsWith("jokes")) {
			sendMessage(channel, "We will now retrieve a joke for you");

			String website = "https://official-joke-api.appspot.com/random_joke";

			String jsonArray = "";
			try {
				jsonArray = JokesAPI.httpGetJokes(website);
			} catch (IOException e) {
				sendMessage(channel, "Call Failed: " + e);
			}

			// System.out.println(jsonArray);

			OfficialJokesObject jokeObject = JokesAPI.parseJson(jsonArray);

			sendMessage(channel, jokeObject.setup + " Press Enter to see the answer");

			sendMessage(channel, jokeObject.punchline);

		}
	}

}
