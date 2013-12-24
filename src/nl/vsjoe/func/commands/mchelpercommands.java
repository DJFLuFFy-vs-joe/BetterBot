package nl.vsjoe.func.commands;

public class McHelperCommands extends DefaultCommands {

	protected void helper(String channel, String sender, String login, String hostname, String message, String[] msg ) {
		if (msg[2].equalsIgnoreCase("!online")) {
			askOnline(channel);
		}
		if (msg[2].equalsIgnoreCase("!aanmelden")) {
			aanmelden(channel);
		}
		if (msg[2].equalsIgnoreCase("!forum")) {
			forum(channel);
		}
		if (msg[2].equalsIgnoreCase("!ts")) {
			teamSpeak(channel);
		}
		if (msg[2].equalsIgnoreCase("!fun")) {
			fun(channel);
		}
	}
}
