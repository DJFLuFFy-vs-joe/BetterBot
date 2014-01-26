package nl.vsjoe;

import nl.vsjoe.ref.Cfg;

import org.jibble.pircbot.Colors;
import org.jibble.pircbot.User;

public class IrcListener extends botConsole {
	//public chat listener
	private int timer = 0;
	
	public void onMessage(String channel, String sender, String login, String hostname, String message){
		if(!Cfg.Debug) {
			System.out.println("<" + sender + "> " +  nameFormatter(message, sender));
			if (sender == Cfg.IRCNick) {
				timer = 0;
			}
			if (timer == 512) {
				fun(channel);
				timer = 0;
			} else {
				timer = timer + 1;
			}
		}
		if (antispam > 0) {
			antispam = antispam - 1;
		}
		//lets make an array of the messages and call it msg[]
		String[] msg = message.split(" ");
		//lets start listening to the minecraft servers
		if (sender.equals("DMC")
				|| sender.equals("HAX")
				|| sender.equals("DTE")
				|| sender.equals("ENG")) {
			spelers(channel, sender, login, hostname, message, msg);
			//lets see if the message is send by a helper
			if (msg[0].contains(Colors.MAGENTA)){
				helper(channel, sender, login, hostname, message, msg);
			}
			//this checks if player is mod or admin and not a D+
			if (msg[0].contains(Colors.CYAN)
					|| msg[0].contains(Colors.OLIVE)
					&& !msg[0].contains(Colors.TEAL)){
				helper(channel, sender, login, hostname, message, msg);
				adMod(channel, sender, login, hostname, message, msg);
				if (Cfg.ActiveCloud == true) {
					cloudBot(channel, sender, login, hostname, message, msg);
				}
			}	
		}
		// END OF minecraft server listener
		
		// This listens to IrcOp's
		User users[] = getUsers( channel );
		User u = null;

		for (User user : users) {
			if( sender.equals(user.getNick() ) ){
				u = user;
				break;
			}
		}
		if( u != null ){
			if ( u.getPrefix() == "@" || u.getPrefix() == "&" ) {
				if ( !sender.equals("DMC") ) {
					IrcOP(channel, sender, login, hostname, message, msg);
				}
			}
		}
		//END OF Irc OP listener
	}
	//Private message listener
	public void onPrivateMessage(String sender, String login, String hostname, String message) {
		System.out.println("[PM] [" + sender + "] " + message);
		if(sender.matches(Cfg.CloudBot)) {
			sendMessage(Cfg.IRCChannel, message);
		} else if (message.contains("!trustme " + Cfg.TRUSTCODE)) {
			op(Cfg.IRCChannel, sender);
			sendMessage(sender, "gefeliciteerd " + sender + " je bent nu OP.");
			sendAction(Cfg.IRCChannel, "Heeft " + sender + " Operator gemaakt!");
		} else {
			sendMessage(sender, "Sorry maar ik begrijp niet wat je zegt.");
		}
	} //End OF Private message listener
	//This will listen to actions 
	public void onAction(String sender, String login, String hostname, String target, String action) {
		System.out.println("* " + sender + " " + action);
		if (sender.equals(Cfg.CloudBot) && (target.equals(Cfg.IRCNick)) ) {
			cloudAction(Cfg.IRCChannel,action);
		}
	}//END OF Action listener
	//This will listen to Notices
	public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
		System.out.println("NOTICE - " + sourceNick + " - " + notice);
		if (sourceNick.equals(Cfg.CloudBot) && (target.equals(Cfg.IRCNick)) ) {
			cloudAction(Cfg.IRCChannel,notice);
		}
	}
}

