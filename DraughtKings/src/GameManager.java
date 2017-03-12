import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager 
{
	NetworkManager nm;
	Boolean playerTurn;
	public GameManager(NetworkManager nm) {
		this.nm = nm;
		playerTurn = nm.isHost;
	}
	public void start()
	{
		JFrame frame = new JFrame("Test Window");
		GameBoard board = new GameBoard(8, 75, nm.isHost);
		//Left hand display
		frame.getContentPane().add(new JPanel(), BorderLayout.WEST);
		//the game board
		frame.getContentPane().add(board, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth() + 1000, frame.getHeight() + 1000);
		frame.setVisible(true);
		
		while(!board.gameOver()) {
			if (playerTurn) {
				
			}
		}
	}
	
}
