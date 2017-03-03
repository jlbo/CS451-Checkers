import java.awt.*;

import javax.swing.JPanel;


public class GameBoard extends JPanel {

	private int tileWidth;
	private int boardWidth;
	
	private static final Color RED = Color.RED;
	private static final Color BLACK = Color.BLACK;
	
	public GameBoard()
	{
		//Call super constructor
		super();
		tileWidth = 75;
		boardWidth = 8;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard((Graphics2D) g);
	}
	
	private void drawBoard(Graphics2D g)
	{
		g.setColor(RED);
		for (int x = 0; x < boardWidth; x++)
		{
			g.setColor(redOrBlack(g.getColor()));
			for (int y = 0; y < boardWidth; y++)
			{
				g.fillRect(x*tileWidth, y*tileWidth, tileWidth, tileWidth);
				g.setColor(redOrBlack(g.getColor()));
			}
		}
	}
	
	private Color redOrBlack(Color c)
	{
		if (c.equals(BLACK))
			return RED;
		else
			return BLACK;
	}

}
