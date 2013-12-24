package nl.vsjoe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import nl.vsjoe.func.commands.CloudBotListener;
import nl.vsjoe.ref.Cfg;

public class botConsole extends CloudBotListener {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public void ConsoleReader() {

		while (true) {
			String inputMsg = null;
			try {
				inputMsg = br.readLine();
				String[] inMsg = inputMsg.split(" ");
				if (inMsg[0].contains("/")) {
					StringBuilder builder = new StringBuilder();
					String command = inMsg[0];
					inMsg[0] = "";
					for (String string : inMsg) {
						if (builder.length() > 0) {
							builder.append(" ");
						}
						builder.append(string);
					}
					String parameters = builder.toString();
					InternalCommand(command, parameters);
				} else {
					sendMessage(Cfg.IRCChannel, inputMsg);
				}
			} catch (IOException ioe) {
				//nothing here
			}
		}
	}
	
	private void InternalCommand(String command, String parameters){
		if (command.equalsIgnoreCase("/me")) {
			sendAction(Cfg.IRCChannel, parameters);
		}
		if (command.equalsIgnoreCase("/debug")) {
			if (Cfg.Debug == true) {
				Cfg.Debug = false;
				System.out.println("Debugger staat nu aan");
			} else {
				Cfg.Debug = true;
				System.out.println("Debugger staat nu aan");
			}
			try {
				Cfg.createConfig(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
