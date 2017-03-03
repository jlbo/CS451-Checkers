import java.awt.*;

import javax.swing.JPanel;


public class GameBoard extends JPanel {

	private int tileWidth;
	private int boardWidth;
	
	private Tile[][] tiles = new Tile[8][8];
	
	private static final Color RED = new Color(0, 138, 213);
	private static final Color BLACK = Color.GRAY;
	
	public GameBoard()
	{
		//Call super constructor
		super();
		tileWidth = 75;
		boardWidth = 8;
		initializeTiles();
//		this.setLayout(new GridLayout(boardWidth, boardWidth));
	}
	
	private void initializeTiles()
	{
		//black pieces
		for (int row = 0; row < boardWidth; row++)
		{
			for (int col = 0; col < boardWidth; col++)
			{
				if (row < 3)
				{
					//black pieces
					tiles[row][col] = checkPiece(row, col, Tile.BLACK);
				}
				else if (row > 4)
				{
					//red pieces
					tiles[row][col] = checkPiece(row, col, Tile.RED);
				}
				else
				{
					tiles[row][col] = Tile.EMPTY;
				}
				
			}
		}
	}
	
	private Tile checkPiece(int row, int col, Tile color)
	{
		//Even rows, odd cols, place a piece
		if (isEven(row) && !isEven(col))
		{
			return color;
		}//odd rows, even cols, place a piece
		else if (!isEven(row) && isEven(col))
		{
			return color;
		}
		else
		{
			return Tile.EMPTY;
		}
	}

	private boolean isEven(int num) {
		return num % 2 == 0;
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
				g.drawString(tiles[y][x].toString(), x*tileWidth, y*tileWidth +15);
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
	
	public enum Tile
	{
		EMPTY("empty"),
		BLACK("black"),
		RED("red");
		
		private final String _name;
		Tile(String name)
		{
			_name = name;
		}
		
		public String toString()
		{
			return _name;
		}
	}

}
