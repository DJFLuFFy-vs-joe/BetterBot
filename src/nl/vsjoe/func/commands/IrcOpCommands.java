package nl.vsjoe.func.commands;

public class IrcOpCommands extends McAdModCommands {
	protected void IrcOP(String channel, String sender, String login, String hostname, String message, String[] msg ) {
		if (msg[0].equalsIgnoreCase("!online")) { 
			askOnline(channel);
		}
		if (msg[0].equalsIgnoreCase("!vote")) {
			vote(channel);
		}
		if (msg[0].equalsIgnoreCase("!help")){
			help(channel);
		}
		if (msg[0].equalsIgnoreCase("!ip") && !( 3 >= msg.length)) {
				if ( msg[1].equalsIgnoreCase("DMC")
				|| msg[1].equalsIgnoreCase("DTE")
				|| msg[1].equalsIgnoreCase("HAX")) {
			serverIp(channel, msg[3]);
				}
		}
		if (msg[0].equalsIgnoreCase("!website")) {
			website(channel);
		}
		if(msg[0].equalsIgnoreCase("!fun")) {
			fun(channel);
		}
		if ( msg[0].equalsIgnoreCase("!reboot") || msg[0].equalsIgnoreCase("!restart")) {
			if ( !( 1 >= msg.length)) {
				if (   msg[1].equalsIgnoreCase("HAX") 
					|| msg[1].equalsIgnoreCase("DMC") 
					|| msg[1].equalsIgnoreCase("DTE")
					|| msg[1].equalsIgnoreCase("ENG")) {
					rebootMCServer(channel, msg[1]);
				} else {
					rebootError(channel, sender );
				}
			} else {
				rebootError(channel, sender);
			}
		}
		if (msg[0].equalsIgnoreCase("!cb")) {
			if ( !(1 >= msg.length)) {
				CBSwitch(channel, msg[1] );
			} else {
				CBSwitch(channel, "error");
			}
		}
		if (msg[0].equalsIgnoreCase("!CBName")) {
			if ( !(1 >= msg.length )) {
			CBName(channel, msg[1]);
			} else {
				CBName(channel, "Error");
			}
		}
	}
}
