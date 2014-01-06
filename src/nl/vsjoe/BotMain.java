package nl.vsjoe;

import nl.vsjoe.ref.Cfg;

public class BotMain {

	public static void main (String[] args) throws Exception {
		
		Cfg.Properties();
		
		// Maak bot object aan
		IrcListener bot = new IrcListener();
		
		// Debug mode
		bot.setVerbose(Cfg.Debug);
				
		// Verbind met IRC server
		bot.connect(Cfg.IRCServer);
		
		// Verbind met kanaal en identificeer bij NickServ
		bot.joinChannel(Cfg.IRCChannel);
		bot.identify(Cfg.IRCNickServ);
		
		bot.ConsoleReader();
	}

}