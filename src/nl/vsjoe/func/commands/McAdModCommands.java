package nl.vsjoe.func.commands;

public class McAdModCommands extends McHelperCommands {

	protected void adMod(String channel, String sender, String login, String hostname, String message, String[] msg ) {
		if (msg[2].equalsIgnoreCase("!kick") || msg[2].equalsIgnoreCase("!ban")) {
			kickOrBan(channel, hostname, msg);			
		}
		if (msg[2].equalsIgnoreCase("!CBName")) {
			if ( !(3 >= msg.length )) {
			CBName(channel, msg[3]);
			} else {
				CBName(channel, "Error");
			}
		}
		if (msg[2].equalsIgnoreCase("!cb")) {
			if ( !(3 >= msg.length)) {
				CBSwitch(channel, msg[3] );
			} else {
				CBSwitch(channel, "error");
			}
		}
		if ( msg[2].equalsIgnoreCase("!reboot") || msg[2].equalsIgnoreCase("!restart")) {
			if ( !( 3 >= msg.length)) {
				if (   msg[3].equalsIgnoreCase("HAX") 
					|| msg[3].equalsIgnoreCase("DMC") 
					|| msg[3].equalsIgnoreCase("DTE")
					|| msg[3].equalsIgnoreCase("ENG")) {
					rebootMCServer(channel, msg[3]);
				} else {
					rebootError(channel, msg[1] );
				}
			} else {
				rebootError(channel, msg[1]);
			}
		} 
	}
}
