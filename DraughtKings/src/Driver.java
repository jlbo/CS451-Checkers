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
		NetworkManager nm;
		
		if (/*clicks host game*/) {
			nm = hostGame();
		} else if (/*clicks join game*/) {
			String ipaddr = null;//get IP address here
			nm= joinGame(ipaddr);
		}
		
		gm = new GameManager(nm);
		gm.start();
	}

}
