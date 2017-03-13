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
		String winTitle = nm.isHost ? "Team Red" : "Team Black";
		JFrame frame = new JFrame(winTitle);
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
			if (board.isMyTurn()) {
				try {
					Thread.sleep(500);
				} catch (Exception ex) {
					//failed	
				}
				if (board.isMoved()) {
					System.out.println("Did it get to send?");
					
					if (nm.sendMove(board.getLastMove())) {
					
						System.out.println("It sent");
						board.setMoved(false);
						board.setMyTurn(false);
					}
					else 
						System.out.println("sendMove returned false");
				}
			} else {
				Move newMove = nm.readMove();
				if (newMove != null) {
					board.updateBoard(newMove);
					board.setMyTurn(true);
					System.out.println("newMove successfully recieved");
				}
				else
					System.out.println("newMove did not get recieved succefully");
			}
		}
		frame.dispose();
	}
	
}
