package nl.vsjoe;

import nl.vsjoe.ref.Cfg;

import org.jibble.pircbot.*;

public class IrcConnector extends PircBot {
	
	public IrcConnector() {
		this.setName(Cfg.IRCNick);
	}
	public void onKick(String channel, String kickerNick, String login, String hostname, String recipientNick, String reason) {
		if (recipientNick.equalsIgnoreCase(getNick())) {
			joinChannel(channel);
		}

	}
	// This will try to reconnect when the connection is lost. [UNTESTED]
	public void onDisconnect() {

		while (!isConnected()) {
			try {
				reconnect();
			}
			catch (Exception e) {
				// Couldn’t reconnect.
				// Pause for a short while before retrying?
			}
		}

	}
}
