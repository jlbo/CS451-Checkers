import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JPanel;


public class GameBoard extends JPanel {

	private int tileWidth;
	private int boardWidth;
	
	private Tile[][] tiles = new Tile[8][8];
	private HashMap<Location, GamePiece> redPieces = new HashMap<Location, GamePiece>();
	private HashMap<Location, GamePiece> blackPieces = new HashMap<Location, GamePiece>();
	
	private static final Color HIGHLIGHT = new Color(255, 255, 0, 155);
	private static final Color RED = new Color(0, 138, 213);
	private static final Color RED_PIECE = Color.RED;
	
	private static final Color BLACK = Color.GRAY;
	private static final Color BLACK_PIECE = Color.BLACK;
	
	public GameBoard()
	{
		//Call super constructor
		super();
		tileWidth = 75;
		boardWidth = 8;
		initializeTiles();
		MyMouseAdapter actionListener = new MyMouseAdapter(this);
		this.addMouseListener(actionListener);
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
					Location pos = new Location(col, row);
					blackPieces.put(pos, new CheckerPiece(pos, tileWidth, BLACK_PIECE));
				}
				else if (row > 4)
				{
					//red pieces
					tiles[row][col] = checkPiece(row, col, Tile.RED);
					Location pos = new Location(col, row);
					redPieces.put(pos, new CheckerPiece(pos, tileWidth, RED_PIECE));
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
	
	public void redrawTile(Point p)
	{
		Location loc = new Location(p, tileWidth);
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(HIGHLIGHT);
		g.fillRect(loc.getX() * tileWidth, loc.getY() * tileWidth, tileWidth, tileWidth);
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
				Tile currTile = tiles[y][x];
				if (currTile != Tile.EMPTY)
				{
					if (currTile == Tile.BLACK)
					{
						blackPieces.get(new Location(x, y)).draw(g);
					} 
					else if (currTile == Tile.RED)
					{
						redPieces.get(new Location(x, y)).draw(g);
					}
				}
				g.setColor(redOrBlack(g.getColor()));
				
//				g.drawString(tiles[y][x].toString(), x*tileWidth, y*tileWidth +15);
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
