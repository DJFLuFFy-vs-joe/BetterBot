package nl.vsjoe.func.commands;

import nl.vsjoe.func.IrcFunctions;

public class DefaultCommands extends IrcFunctions {
	
	protected void spelers(String channel, String sender, String login, String hostname, String message, String[] msg ) {
		if (msg[2].equalsIgnoreCase("!vote")) {
			vote(channel);
		}
		if (msg[2].equalsIgnoreCase("!help")){
			help(channel);
		}
		if (msg[2].equalsIgnoreCase("!ip") && !( 3 >= msg.length)) {
				if ( msg[3].equalsIgnoreCase("DMC")
				|| msg[3].equalsIgnoreCase("DTE")
				|| msg[3].equalsIgnoreCase("HAX")) {
			serverIp(channel, msg[3]);
				}
		}
		if (msg[2].equalsIgnoreCase("!website")) {
			website(channel);
		}
		if (msg[1].equalsIgnoreCase("was") && msg[6].contains("Flying")) {
			flyModDetect(channel, sender, msg[0]);
		}
	}

}
