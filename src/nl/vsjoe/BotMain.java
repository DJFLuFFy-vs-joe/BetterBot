package nl.vsjoe;

import nl.vsjoe.ref.Cfg;

public class BotMain {

	public static void main (String[] args) throws Exception {
		
		Cfg.Properties();
		
		// make a Bot Object
		IrcListener bot = new IrcListener();
		
		// Debug mode
		bot.setVerbose(Cfg.Debug);
				
		// Connect to the IRC server
		bot.connect(Cfg.IRCServer);
		
		// Connect to the channel and enter NickServ password
		bot.joinChannel(Cfg.IRCChannel);
		bot.identify(Cfg.IRCNickServ);
		
		bot.ConsoleReader();
	}

}
