import javax.swing.*;

import java.awt.*;
import java.util.HashMap;


public class Driver {

	boolean isHost;
	
	public void clientManager(){
		this.isHost = false;
	}
	
	public void hostManager(){
		this.isHost = true;
	}
	
	public void exitGame(){
		System.exit(0);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Window");
		GameBoard board = new GameBoard();
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
