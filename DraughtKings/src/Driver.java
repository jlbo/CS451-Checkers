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
		JFrame frame = new JFrame("Test Window");
		GameBoard board = new GameBoard(8, 75);
		//Left hand display
		frame.getContentPane().add(new JPanel(), BorderLayout.WEST);
		//the game board
		frame.getContentPane().add(board, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth() + 1000, frame.getHeight() + 1000);
		frame.setVisible(true);
		
		Move mv = new Move();
	}

}
