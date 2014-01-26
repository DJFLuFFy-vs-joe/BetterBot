package nl.vsjoe.func;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jibble.pircbot.Colors;

import nl.vsjoe.IrcConnector;
import nl.vsjoe.ref.Cfg;
import nl.vsjoe.ref.FunLines;

@SuppressWarnings("deprecation")
public class IrcFunctions extends IrcConnector {

	public int antispam = 0;

	// Online Query
	protected void askOnline(String channel) {
		sendMessage(channel, ".players");
	}
	//fun Script
	protected void fun(String channel) {
		if (antispam == 0) { 
			Random quote = new Random();
			int RandomLine = quote.nextInt(FunLines.FUNNYLINES.length);
			sendMessage(channel, FunLines.FUNNYLINES[RandomLine]);
			antispam = antispam + 25;
		}
	}
	//Sleep Script
	protected void Sleep(int SleepTime) {
		try {
			Thread.sleep(SleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//Help Command
	protected void help(String channel) {
		sendMessage(channel, Colors.MAGENTA + "Voor Hulp ga naar " + Colors.NORMAL + Cfg.HELP);
	}
	//Vote Command
	protected void vote(String channel) {
		sendMessage(channel, Colors.MAGENTA + "Ga naar " + Cfg.VOTE + " om te stemmen!");
	}
	//Aanmelden Command
	protected void aanmelden(String channel) {
		sendMessage(channel, Colors.CYAN + Cfg.AANMELDEN );
	}
	//website command
	protected void website(String channel) {
		sendMessage(channel, Colors.CYAN + "Bezoek onze website op " + Colors.NORMAL + Cfg.WEBSITE );
	}
	//Forum Command
	protected void forum(String channel) {
		sendMessage(channel, Colors.CYAN + "Bezoek ons Forum op " + Colors.NORMAL + Cfg.FORUM );
	}
	//Team Speak Command
	protected void teamSpeak(String channel) {
		sendMessage(channel, Colors.CYAN + "Onze Teamspeak server is " + Colors.NORMAL + Cfg.TS3 );
	}
	//Server IP this will format the server ip command and send it to sendIp
	protected void serverIp(String channel, String msg) {
		if (msg.equalsIgnoreCase("DMC")) {
			sendIp(channel, msg, Cfg.DMCIP);
		}
		if (msg.equalsIgnoreCase("DTE")) {
			sendIp(channel, msg, Cfg.DTEIP);
		}
		if (msg.equalsIgnoreCase("HAX")) {
			sendIp(channel, msg, Cfg.HAXIP);
		}
	}
	//this will receive the strings from serverIP and send it formatted to the chat.
	protected void sendIp(String channel, String msg, String url) {
		sendMessage(channel, Colors.CYAN + "Het ip van de " + msg + " server is " + Colors.NORMAL + url);
	}
	//kick and ban script
	protected void kickOrBan(String channel, String hostname, String[] msg) {
		if (msg[3].equalsIgnoreCase(Cfg.IRCNick) 
				|| (msg[3].equalsIgnoreCase("DMC"))
				|| (msg[3].equalsIgnoreCase("HAX"))
				|| (msg[3].equalsIgnoreCase("DTE"))
				|| (msg[3].equalsIgnoreCase("ENG"))) {
			sendMessage(channel, Colors.RED + "Sorry " + msg[1] + Colors.RED + " maar dit kan ik niet toestaan.");
		} else {
			boolean ban = false;
			if (msg[2].equalsIgnoreCase("!ban")) {
				ban = true;
			}
			StringBuilder sKick = new StringBuilder();
			msg[0] = "";
			msg[1] = "";
			msg[2] = "";
			for (String string : msg) {
				if (sKick.length() > 0) {
					sKick.append(" ");
				}
				sKick.append(string);
			}
			String reason = sKick.toString();

			if ( !(4 >= msg.length)) {
				if (ban == true) {
					ban(channel, hostname);
				}
				kick(channel,msg[3], reason );
			}

			else if ( !(3 >= msg.length)) {
				if (ban == true ) {
					ban(channel, hostname);
				}
				kick(channel, msg[3], "My Channel My rules");
			} else {
				if(ban == true) {
					msg[2] = "!Ban";
				} else {
					msg[2] = "!Kick";
				}
				sendMessage(channel, Colors.RED + "Sorry " + msg[1] + Colors.RED + " maar je doet het helemaal fout!");
				sendMessage(channel, Colors.RED + "Probeer eens "  + msg[2] + " <naam> <reden> ");
			}
		}
	}
	//this will send a pm to Cloudbot and will send his reply back in to the chat 
	protected void CBAction(String botName, String[] msg) {
		StringBuilder builder = new StringBuilder();

		msg[0] = "";
		msg[1] = "";

		for (String string : msg) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(string);
		}
		String string = builder.toString();
		sendMessage(botName, string);
	}
	//protected void 
	//This will change cloudbots name or tell us his name
	protected void CBName(String channel, String botname) {
		if ( !botname.equalsIgnoreCase("Error")) {
			Cfg.CloudBot = botname;
			try {
				Cfg.createConfig(false);
			} catch (FileNotFoundException e) {
				sendMessage(channel, Colors.RED + "FOUT: Kan het config bestand niet vinden!");
				e.printStackTrace();
			} catch (IOException e) {
				sendMessage(channel, Colors.RED + "Er is een Onbekende Fout opgetreden!");
				e.printStackTrace();
			}
			sendMessage(channel, "Ik kan nu communiceren met " + Colors.OLIVE + Cfg.CloudBot);
		} else {
			sendMessage(channel, "Volgens mij heet CloudBot nu " + Colors.OLIVE + Cfg.CloudBot );
		}
	}
	//This will Turn the Communication with the CloudBot on or off
	protected void CBSwitch(String channel, String action) {
		if (action.equalsIgnoreCase("on")) {
			Cfg.ActiveCloud = true;
			sendMessage(channel,Colors.OLIVE + Cfg.CloudBot + Colors.NORMAL + " staat nu" + Colors.GREEN + " Aan" );
		} else if (action.equalsIgnoreCase("off")) {
			Cfg.ActiveCloud = false;
			sendMessage(channel,Colors.OLIVE + Cfg.CloudBot + Colors.NORMAL + " staat nu" + Colors.RED + " Uit" );
		} else if (action.equalsIgnoreCase("error")) {
			sendMessage(channel, Colors.RED + "Het juiste commando is !CB ON of !CB OFF");
		} else {
			sendMessage(channel, Colors.RED + "Fout: " + action + " is geen geldig Commando");
			sendMessage(channel, Colors.RED + "Het juiste commando is !CB ON of !CB OFF");
		}
	}
	protected void rebootMCServer(String channel, String msg ) {
		sendMessage(channel, Colors.RED + "De " + msg +  " server word over " + Cfg.WAITTIME / 1000 + " seconden opnieuw opgestart.");
		Sleep(Cfg.WAITTIME);
		if (msg.equalsIgnoreCase("DMC")) {
			try {
				sendPost(Cfg.DMC);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (msg.equalsIgnoreCase("DTE")) {
			try {
				sendPost(Cfg.DTE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (msg.equalsIgnoreCase("ENG")) {
			try {
				sendPost(Cfg.ENG);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (msg.equalsIgnoreCase("HAX")) {
			try {
				sendPost(Cfg.HAX);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	protected void rebootError(String channel, String name ) {
		sendMessage(channel, Colors.RED + "Sorry " + name + Colors.RED + " maar je doet het helemaal fout!");
		sendMessage(channel,Colors.RED + "Probeer eens" + Colors.GREEN + " !restart servernaam" + Colors.RED + " bijvoorbeeld"+ Colors.GREEN + " !restart DMC " + Colors.RED + "voor de vanilla server");
	}

	// HTTP POST request
	private void sendPost(String ServerUrl) throws Exception {

		String url = ServerUrl;

		@SuppressWarnings({ "resource" })
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", "USER_AGENT");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "Reboot"));


		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
				response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result.toString());
	}

	//Flymod functions
	//FLymod array
	public String[] FlyMod = { "onbekend","onbekende" };
	protected void flyModDetect(String channel, String sender, String user ) {
		FlyMod[0] = user;
		FlyMod[1] = sender;
		sendMessage(channel, Colors.GREEN + "[TIP] " + Colors.MAGENTA + "Wij detecteren het gebruik van Flymod. Gebruik wordt bestraft met een ban.");
		String sendName = nameFormatter(user, sender);
		AddToFlyModDB(sendName,sender);
	}
	
	protected String nameFormatter(String name, String server){
		switch(server) {
		case "HAX":
			return name.substring(2);
		default:
			return name.substring(3);	
		}

	}
	
	protected void displayFlyMod(String channel) {
		sendMessage(channel, Colors.RED + FlyMod[0] + Colors.NORMAL + " Heeft als laatste flymod gebruikt op de " + Colors.RED + FlyMod[1] + Colors.NORMAL + " server.");
	}
	
	protected void AddToFlyModDB(String name, String server) {
		try {
			// open a connection to the site
			URL url = new URL(Cfg.FLYMODURL);
			URLConnection con = url.openConnection();
			// activate the output
			con.setDoOutput(true);
			PrintStream ps = new PrintStream(con.getOutputStream());
			// send your parameters to your site
			ps.print("firstKey=firstValue");
			ps.print("&secondKey=secondValue");
			ps.print("&playername=" + name);
			ps.print("&server=" + server);

			// we have to get the input stream in order to actually send the request
			con.getInputStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			// close the print stream
			ps.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//End OF FLyMod Functions

	protected void loterij(String channel, String StrMaxNumber) {
		Random number = new Random();

		try {
			int maxNumber = Integer.parseInt(StrMaxNumber);
			if (maxNumber > 1) {
				int RandomLot = number.nextInt(maxNumber);
				sendMessage(channel, Colors.OLIVE + "Het winnende lot van de loterij is " + Colors.GREEN + RandomLot + Colors.OLIVE + " er deden " + maxNumber + " loten mee!");
			} else {
				sendMessage(channel, Colors.RED + "Sorry, maar " + StrMaxNumber + " is een te laag getal om een loterij te houden.");
			}
		} catch (NumberFormatException e) {
			sendMessage(channel, Colors.RED + "Sorry, maar " + StrMaxNumber + " is geen getal.");
			sendMessage(channel, Colors.RED + "Probeer !loterij getal <!loterij 20>.");
		}

	}

}