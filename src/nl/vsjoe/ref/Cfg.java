package nl.vsjoe.ref;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Cfg {

	public static Boolean Debug = false;

	public static String IRCServer = null;
	public static String IRCChannel = null;
	public static String IRCNick = null;
	public static String IRCNickServ = null;
	public static String CloudBot = null;
	public static String TRUSTCODE = null;

	//URL's to Reboot Scripts
	public static String HAX = "http://dummy.url.com";
	public static String DMC = "http://dummy.url.com";
	public static String DTE = "http://dummy.url.com";
	public static String ENG = "http://dummy.url.com";

	//Time to wait for a server Restart
	public static int WAITTIME = 10000;

	//URL's to help new players
	public static String AANMELDEN = "http://dummy.url.com";
	public static String WEBSITE = "http://dummy.url.com";
	public static String FORUM = "http://dummy.url.com";
	public static String TS3 = "http://dummy.url.com";
	public static String DTEIP = "http://dummy.url.com";
	public static String DMCIP = "http://dummy.url.com";
	public static String HAXIP = "http://dummy.url.com";
	public static String VOTE = "http://dummy.url.com";
	public static String HELP = "http://dummy.url.com";

	//Boolean to switch Cloudbot Communication on/off
	public static Boolean ActiveCloud = true;

	public static void Properties() throws FileNotFoundException, IOException {
		try {
			loadConfig();
		} catch (FileNotFoundException e) {
			createConfig(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createConfig(Boolean install) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Properties prop = new Properties();
		if (install == true) {
			//set the properties value
			String inCloudBot = "CloudBot";
			String inTrust = "TrustCode";

			System.out.println("Welcome to BetterBot Made By DJFLuFFy vs joe");
			System.out.println();
			System.out.println("Setup");
			System.out.println();
			System.out.print("What is the server's Ip adress?:");
			String inServerMsg = br.readLine();
			System.out.println();
			System.out.print("Please Enter the nickname you want to use:");
			String inNickMsg = br.readLine();
			System.out.println();
			System.out.println("Please enter the name of the channel you want to join without the # symbol");
			System.out.print("#");
			String inChannel = "#" + br.readLine();
			System.out.println();
			System.out.print("Optional NickServ password:");
			String inNServ = br.readLine();
			System.out.println("if you type advanced you will be able to set more settings.");
			System.out.println("if you press ENTER the Bot starts connecting to the IRC.");
			String Continue = br.readLine();
			if(Continue.equalsIgnoreCase("advanced")) {
				System.out.println();
				System.out.print("What is the Name of the active Cloudbot?:");
				inCloudBot = br.readLine();
				System.out.println();
				System.out.println("You become an OP if you PM to this bot and type !trustme and the Trust code");
				System.out.print("Please enter a Trustcode:");
				inTrust = br.readLine();
			} 
			//Main Settings these use to be startup args
			prop.setProperty("IRC_Server_Adress", inServerMsg);
			prop.setProperty("IRC_Channel", inChannel);
			prop.setProperty("IRC_NickName", inNickMsg);
			prop.setProperty("NickServ_Password", inNServ);
			prop.setProperty("CloudBot_Name", inCloudBot);
			prop.setProperty("TrustCode", inTrust);
		} else {
			prop.setProperty("IRC_Server_Adress", IRCServer);
			prop.setProperty("IRC_Channel", IRCChannel);
			prop.setProperty("IRC_NickName", IRCNick);
			prop.setProperty("NickServ_Password", IRCNickServ);
			prop.setProperty("CloudBot_Name", CloudBot);
			prop.setProperty("TrustCode", TRUSTCODE);
		}
		//Adding the restart URL's
		prop.setProperty("Restart_HAX", HAX);
		prop.setProperty("Restart_DMC", DMC);
		prop.setProperty("Restart_DTE", DTE);
		prop.setProperty("Restart_ENG", ENG);

		//Waiting time in Seconds
		StringBuilder time = new StringBuilder();
		time.append(WAITTIME / 1000);
		prop.setProperty("Waiting_Time_in_seconds", time.toString());

		//HelpFull URL's to help the players on the server.
		prop.setProperty("URL_Aanmelden", AANMELDEN);
		prop.setProperty("URL_Website", WEBSITE);
		prop.setProperty("URL_Forum", FORUM);
		prop.setProperty("URL_Stemmen", VOTE);
		prop.setProperty("URL_HelpForumPage", HELP);
		prop.setProperty("IPAdress_TeamSpeak", TS3);
		prop.setProperty("IPAdress_DMC", DMCIP);
		prop.setProperty("IPAdress_DTE", DTEIP);
		prop.setProperty("IPAdress_HAX", HAXIP);
		//Setting the Debug setting
		if(Debug == false){ 
			prop.setProperty("Debug", "false");
		} else {
			prop.setProperty("Debug", "true");
		}

			//save properties to project root folder
			prop.store(new FileOutputStream("BetterBot.config"), null);
		System.out.println("Config File created");
		System.out.println();

		loadConfig();
	}

	public static void loadConfig() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		//load a properties file
		prop.load(new FileInputStream("BetterBot.config"));

		//get the property value and print it out
		IRCServer = prop.getProperty("IRC_Server_Adress");
		IRCChannel = prop.getProperty("IRC_Channel");
		IRCNick = prop.getProperty("IRC_NickName");
		IRCNickServ = prop.getProperty("NickServ_Password");
		CloudBot = prop.getProperty("CloudBot_Name");
		TRUSTCODE = prop.getProperty("TrustCode");

		// fetching the urls to the server startup pages 

		HAX = prop.getProperty("Restart_HAX");
		DMC = prop.getProperty("Restart_DMC");
		DTE = prop.getProperty("Restart_DTE");
		ENG = prop.getProperty("Restart_ENG");

		//getting the waiting time 

		String getTime = prop.getProperty("Waiting_Time_in_seconds");
		WAITTIME = Integer.parseInt(getTime) * 1000;

		//fetching helpfull urls
		AANMELDEN = prop.getProperty("URL_Aanmelden");
		WEBSITE = prop.getProperty("URL_Website");
		FORUM = prop.getProperty("URL_Forum");
		VOTE = prop.getProperty("URL_Stemmen");
		HELP = prop.getProperty("URL_HelpForumPage");
		TS3 = prop.getProperty("IPAdress_TeamSpeak");
		DMCIP = prop.getProperty("IPAdress_DMC");
		DTEIP = prop.getProperty("IPAdress_DTE");
		HAXIP = prop.getProperty("IPAdress_HAX");
		
		//fetching the debug Mode
		if (prop.getProperty("Debug").equalsIgnoreCase("false")) {
			Debug = false;
		} else {
			Debug = true;
		}
	}
}
