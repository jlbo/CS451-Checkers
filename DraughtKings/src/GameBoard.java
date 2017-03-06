import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JPanel;


public class GameBoard extends JPanel {

	private int _tileWidth;
	private int _boardWidth;
	
	private Location _highlighted;
	
	private Tile[][] tiles;
	private HashMap<Location, GamePiece> redPieces = new HashMap<Location, GamePiece>();
	private HashMap<Location, GamePiece> blackPieces = new HashMap<Location, GamePiece>();
	
	private static final Color HIGHLIGHT = new Color(255, 255, 0, 155);
	private static final Color RED = new Color(0, 138, 213);
	private static final Color RED_PIECE = Color.RED;
	
	private static final Color BLACK = Color.GRAY;
	private static final Color BLACK_PIECE = Color.BLACK;
	
	public GameBoard(int boardWidth, int tileWidth)
	{
		//Call super constructor
		super();
		_tileWidth = tileWidth;
		_boardWidth = boardWidth;
		tiles = new Tile[boardWidth][boardWidth];
		initializeTiles();
		MyMouseAdapter actionListener = new MyMouseAdapter(this);
		this.addMouseListener(actionListener);
		setHighlighted(null);
//		this.setLayout(new GridLayout(boardWidth, boardWidth));
	}
	
	private void initializeTiles()
	{
		//black pieces
		for (int row = 0; row < _boardWidth; row++)
		{
			for (int col = 0; col < _boardWidth; col++)
			{
				if (row < 3)
				{
					//black pieces
					Location pos = new Location(col, row);
					tiles[row][col] = checkPiece(pos, Tile.BLACK);
					blackPieces.put(pos, new CheckerPiece(pos, _tileWidth, BLACK_PIECE));
				}
				else if (row > 4)
				{
					//red pieces
					Location pos = new Location(col, row);
					tiles[row][col] = checkPiece(pos, Tile.RED);
					redPieces.put(pos, new CheckerPiece(pos, _tileWidth, RED_PIECE));
				}
				else
				{
					tiles[row][col] = Tile.EMPTY;
				}
				
			}
		}
	}
	
	private Tile checkPiece(Location loc, Tile color)
	{
		int row = loc.getY();
		int col = loc.getX();
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
	
	private Color checkTile(Location loc)
	{
		int row = loc.getY();
		int col = loc.getX();
		if (isEven(row) && !isEven(col))
		{
			return RED;
		}
		else if (isEven(row) && isEven(col))
		{
			return BLACK;
		}
		else if (!isEven(row) && isEven(col))
		{
			return RED;
		}
		return BLACK;
	}
	
	public Location locFromPoint(Point p)
	{
		return new Location(p, _tileWidth);
	}
	
	public void redrawTile(Location loc)
	{
		//What color square
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(checkTile(loc));
		g.fillRect(loc.getX() * _tileWidth, loc.getY() * _tileWidth, _tileWidth, _tileWidth);
		//Piece?
		if (getTile(loc) == Tile.BLACK)
		{
			blackPieces.get(loc).draw(g);
		}
		else if(getTile(loc) == Tile.RED)
		{
			redPieces.get(loc).draw(g);
		}
	}
	
	public void highlightTile(Location loc)
	{
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(HIGHLIGHT);
		g.fillRect(loc.getX() * _tileWidth, loc.getY() * _tileWidth, _tileWidth, _tileWidth);
		setHighlighted(loc);
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
		for (int x = 0; x < _boardWidth; x++)
		{
			for (int y = 0; y < _boardWidth; y++)
			{
				Tile currTile = tiles[y][x];
				Location loc = new Location(x, y);
				//switch color every column
				g.setColor(checkTile(loc));
				g.fillRect(x*_tileWidth, y*_tileWidth, _tileWidth, _tileWidth);
				if (currTile != Tile.EMPTY)
				{
					if (currTile == Tile.BLACK)
					{
						blackPieces.get(loc).draw(g);
					} 
					else if (currTile == Tile.RED)
					{
						redPieces.get(loc).draw(g);
					}
				}
				//switch color every row
				g.setColor(checkTile(loc));
			}
		}
	}
	
	public Location getHighlighted() {
		return _highlighted;
	}

	public void setHighlighted(Location _highlighted) {
		this._highlighted = _highlighted;
	}
	
	public Tile getTile(Location loc)
	{
		return tiles[loc.getY()][loc.getX()];
	}
	
	public boolean selectPiece(Location loc)
	{
		if (getTile(loc) == Tile.EMPTY && getHighlighted() == null)
		{
			//can't click a blank square
			return false;
		}
		if (getHighlighted() == null)
		{
			//not clicking a blank square, so select this piece
			highlightTile(loc);
		}
		else if (getHighlighted() == loc)
		{
			//clicking the already highlighted square, so deselect
			redrawTile(loc);
			setHighlighted(null);
		}
		else
		{
			//TODO:check move between getHighlighted and e.getPoint
			//if move fails, redraw this tile
			redrawTile(getHighlighted());
			setHighlighted(null);
		}
		return true;
	}

}
