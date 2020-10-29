# Chatbot-API-Integration
In this project, I created a chatbot that you can run from your IDE. It connects to the Kiwi IRC Client with Pircbot, and allows access to two API's: OpenWeather and OfficialJokes.

****
## API’s Used 
****
1. OpenWeather
	* Input -> City and ZIP Code
	* Output -> Current Temperature, High, and Low (in Fahrenheit) 
2. OfficialJokes
	* [https://github.com/15Dkatz/official_joke_api](https://github.com/15Dkatz/official_joke_api)
	* Input -> N/A
	* Ouput -> Joke Setup & Punchline

****
## How to execute the project
****
### Using the API’s within your IDE
FUNCTION TO RUN:
	**MainFunction.java**
1. Run MainFunction.java
2. It will prompt you with either choosing to get the weather or the second API
	1. choose A to follow prompts for Weather
	2. choose B to follow prompts for Jokes

### Using the API’s within IRC Client (free node)
FUNCTION TO RUN:
	**PircBotMain.java**
1. Open [Kiwi IRC](https://webchat.freenode.net/) & enter a nickname for yourself, and join the channel ‘#pircbot’
2. Run PircBotMain.java
3. On the freenode site, follow the prompts and the appropriate syntax to call each respective API
4. Type 'sohail' to get the respective commands

****
## Other .java files used
****
#### General
* ParameterStringBuilder.java
	* Called by the API’s when establishing the Http Request connection, gets parameters

#### Jokes API
* JokesAPI.java
	* Calls the OfficialJokes API
* OfficialJokesObject.java
	* Used by gson to parse the JSON into this object

#### Weather API 
* WeatherAPI.java
	* Calls the OpenWeather API
* OpenWeather.java
	* Used by gson to parse the JSON into this object
* OpenWeather Folder (contains all the child objects of the OpenWeather.java class, for retrieving each individual component from the JSON) 
	* Clouds.java
	* Coordinates.java
	* Main.java
	* Sys.java
	* Weather.java
	* Wind.java

#### Pircbot
* MyBot.java
	* Calls the WeatherAPI & JokesAPI. called by PircBotMain in running the bot

