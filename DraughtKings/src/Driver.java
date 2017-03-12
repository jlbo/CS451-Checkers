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
		
//		if (true) { /*clicks host game*/
//			try {
//				nm = hostGame();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else if (true) { /*clicks join game*/
//			String ipaddr = null;//get IP address here
//			try {
//				nm= joinGame(ipaddr);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		boolean host = true;
		if (host) {
			try {
				nm = hostGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				nm = joinGame("localhost");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gm = new GameManager(nm);
		gm.start();
	}

}
