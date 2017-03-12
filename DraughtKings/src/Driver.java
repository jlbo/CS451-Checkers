import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;


public class Driver {

	boolean isHost;
	
	public void exitGame(){
		System.exit(0);
	}
	
	// Use this for hosting a game
	public NetworkManager hostGame() throws IOException { 
		NetworkManager nm = new NetworkManager();
		this.isHost = true;
		return nm;
	}
	
	// Use this when joining a game, Give it the Host's IP address as a String
	public NetworkManager joinGame(String IP) throws IOException {
		NetworkManager nm = new NetworkManager(IP);
		this.isHost = false;
		return nm;
	}
	
	public static void main(String[] args) {

		boolean host = false;
		if (host) {
			NetworkManager nm;
			try {
				nm = new NetworkManager();
				nm.sendAck();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				NetworkManager nm = new NetworkManager("10.250.39.33");
				if (nm.readAck())
					System.out.println("Okie DOkie");
				else
					System.out.println("Oops");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
