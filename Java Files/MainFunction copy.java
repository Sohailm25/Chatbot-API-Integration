import java.io.IOException;
import java.util.Scanner;

public class MainFunction {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);

		System.out.println("Please select an action below:");
		System.out.println("A: Get The Weather");
		System.out.println("B: Get a Joke");

		String choiceString = input.next();
		char choice = choiceString.toUpperCase().charAt(0);

		switch (choice) {
		case 'A':
			WeatherAPI.getWeather(input);
			break;
		case 'B':
			JokesAPI.getJokes(input);
			break;
		}

	}

}
