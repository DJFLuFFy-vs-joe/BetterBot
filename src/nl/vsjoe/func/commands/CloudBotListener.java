package nl.vsjoe.func.commands;

import nl.vsjoe.ref.Cfg;

public class CloudBotListener extends IrcOpCommands {
	
	protected void cloudBot(String channel, String sender, String login, String hostname, String message, String[] msg ){
		if (msg[2].contains(".")) {
			CBAction(Cfg.CloudBot, msg);
		}
	}
	
	protected void cloudPrivate(String channel, String message) {
		sendMessage(channel, message);
	}
	
	protected void cloudAction(String channel, String action) {
		sendAction(channel,action);
	}

}
