import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;


public class Driver {

	public void exitGame(){
		System.exit(0);
	}
	
	// Use this for hosting a game
	public static NetworkManager hostGame() throws IOException { 
		NetworkManager nm = new NetworkManager();
		return nm;
	}
	
	// Use this when joining a game, Give it the Host's IP address as a String
	public static NetworkManager joinGame(String IP) throws IOException {
		NetworkManager nm = new NetworkManager(IP);
		return nm;
	}
	
	public static void main(String[] args) {
		GameManager gm;
		NetworkManager nm = null;
		boolean host;
		String IP = "";
		if (args.length == 0)
			host = true;
		else {
			host = false;
			IP = args[0];
		}
		if (host) {
			try {
				nm = hostGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Network Error");
				System.exit(2);
			}
		} else {
			try {
				nm = joinGame(IP);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error connecting to host");
				System.exit(1);
			}
		}
		gm = new GameManager(nm);
		gm.start();
	}

}
