
public class PircBotMain {

	public static void main(String[] args) throws Exception {

		// create bot
		MyBot bot = new MyBot();

		// display all output for debugging
		bot.setVerbose(true);

		// connect to the IRC Server
		bot.connect("irc.freenode.net");

		// join the #pircbot channel
		bot.joinChannel("#pircbot");

	}

}
