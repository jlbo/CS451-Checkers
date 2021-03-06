import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JPanel;


public class GameBoard extends JPanel 
{

	private int _tileWidth;
	private int _boardWidth;
	
	private Location _highlighted;
	
	private Tile[][] tiles;
	private HashMap<Location, GamePiece> redPieces = new HashMap<Location, GamePiece>();
	private HashMap<Location, GamePiece> blackPieces = new HashMap<Location, GamePiece>();
	
	private static final Color HIGHLIGHT = new Color(255, 255, 0, 155);
	private static final Color RED = new Color(0, 138, 213);
	public static final Color RED_PIECE = Color.RED;
	
	private static final Color BLACK = Color.GRAY;
	public static final Color BLACK_PIECE = Color.BLACK;
	
	private boolean isMyTurn;
	private Move lastMove;
	private boolean moved;
	private boolean _isHost;
	
	public GameBoard(int boardWidth, int tileWidth, boolean isHost)
	{
		//Call super constructor
		super();
		_tileWidth = tileWidth;
		_boardWidth = boardWidth;
		_isHost = isHost;
		tiles = new Tile[boardWidth][boardWidth];
		initializeTiles();
		MyMouseAdapter actionListener = new MyMouseAdapter(this);
		this.addMouseListener(actionListener);
		setHighlighted(null);
//		this.setLayout(new GridLayout(boardWidth, boardWidth));
		this.isMyTurn = isHost;
		this.lastMove = new Move(tiles);
		this.setMoved(false);
	}
	
	public int getBoardWidth()
	{
		return _tileWidth * _boardWidth;
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
					if (tiles[row][col] != Tile.EMPTY)
					{
						blackPieces.put(pos, new CheckerPiece(pos, _tileWidth, BLACK_PIECE, tiles[row][col], this));
					}
				}
				else if (row > 4)
				{
					//red pieces
					Location pos = new Location(col, row);
					tiles[row][col] = checkPiece(pos, Tile.RED);
					if (tiles[row][col] != Tile.EMPTY)
					{
						redPieces.put(pos, new CheckerPiece(pos, _tileWidth, RED_PIECE, tiles[row][col], this));
					}
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
		} else {
            System.out.println("Is it empty?");
        }
	}
	
	public void highlightTile(Location loc)
	{
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(HIGHLIGHT);
		g.fillRect(loc.getX() * _tileWidth, loc.getY() * _tileWidth, _tileWidth, _tileWidth);
		setHighlighted(loc);
	}

	private boolean isEven(int num) 
	{
		return num % 2 == 0;
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		drawBoard((Graphics2D) g);
	}
	
	private void drawBoard(Graphics2D g)
	{
		System.out.println("the board is being drawn");
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
	
	public void updateBoard(Move newMove)
	{
		//Get underlying tile map
		this.tiles = newMove.getTiles();
		
		//try to remove any piece that got captured
		Location captPos = newMove.getEliminated();
		if (captPos != null)
		{
			if (blackPieces.containsKey(captPos))
			{
				blackPieces.remove(captPos);
			}
			else if (redPieces.containsKey(captPos))
			{
				redPieces.remove(captPos);
			}
			redrawTile(captPos);
		}
		
		//Update moved piece/tiles it touched
		updatePiece(newMove.getTo(), newMove.getFrom(), getTile(newMove.getTo()));
	}
	
	private void updatePiece(Location to, Location from, Tile tile)
	{
		if (tile.getColor() == BLACK_PIECE)
		{
			GamePiece piece = blackPieces.remove(from);
            piece.setLocation(to);
			blackPieces.put(to, piece);
		}
		else if (tile.getColor() == RED_PIECE)
		{
			GamePiece piece = redPieces.remove(from);
            piece.setLocation(to);
			redPieces.put(to, piece);
		}
		redrawTile(to);
		redrawTile(from);
        
        System.out.println("Location of black pieces");
        for (Location l : blackPieces.keySet()) {
            System.out.println("("+l.getX()+","+l.getY()+") :: " + blackPieces.get(l).toString());
        }
        
        System.out.println("Location of red pieces");
        for (Location l : redPieces.keySet()) {
            System.out.println("("+l.getX()+","+l.getY()+") :: " + redPieces.get(l).toString());
        }
	}
	
	public Location getHighlighted() 
	{
		return _highlighted;
	}

	public void setHighlighted(Location _highlighted) 
	{
		this._highlighted = _highlighted;
	}
	
	public Tile getTile(Location loc)
	{
		return tiles[loc.getY()][loc.getX()];
	}
	
	public void setTile(Location loc, Tile newT)
	{
		tiles[loc.getY()][loc.getX()] = newT;
	}
	
	private GamePiece getPiece(Location loc)
	{
		Tile tileAt = getTile(loc); 
		if(tileAt == Tile.EMPTY)
		{
			return null;
		} 
		else if (tileAt == Tile.BLACK)
		{
			return blackPieces.get(loc);
		}
		else
		{
			return redPieces.get(loc);
		}
	}
	
	private void movePiece(GamePiece piece, Location from)
	{
		setTile(from, Tile.EMPTY);
		setTile(piece.getLocation(), piece.getTile());
		if (piece.getTile() == Tile.BLACK)
		{
			blackPieces.put(piece.getLocation(), piece);
			blackPieces.remove(from);
		}
		else if (piece.getTile() == Tile.RED)
		{
			redPieces.put(piece.getLocation(), piece);
			redPieces.remove(from);
		}
		Location capturedPos = checkCapture(piece, from);
		lastMove = new Move(tiles);
		lastMove.setEliminated(capturedPos);
		lastMove.setFrom(from);
		lastMove.setTo(piece.getLocation());
//		lastMove.setMovedPiece(piece);
		this.setMoved(true);
		
	}
	
	private Location checkCapture(GamePiece piece, Location from)
	{
		Location newPos = piece.getLocation();
		GamePiece captured = null;
		//did it move TWO squares forward/backward?
		//must have captured
		boolean capture = Math.abs(newPos.getY() - from.getY()) > 1;
		if (capture)
		{
			int x = (piece.getLocation().getX() + from.getX())/2;
			int y = (piece.getLocation().getY() + from.getY())/2;
			Location enemyPos = new Location(x, y);
			System.out.println("piece moved from "+ from + " to " + newPos);
			System.out.println(enemyPos);
			if (piece.getTile() == Tile.BLACK)
			{
				captured = redPieces.remove(enemyPos);
			}
			else if (piece.getTile() == Tile.RED)
			{
				captured = blackPieces.remove(enemyPos);
			}
			setTile(enemyPos, Tile.EMPTY);
			redrawTile(enemyPos);
		}
		return captured != null ? captured.getLocation() : null;
	}
	
	public boolean selectPiece(Location clickPos)
	{
		if(gameOver())
			return false;
		
		if(!this.isMyTurn())
			return false;
		
		
		
		Location highlightedPos = getHighlighted();
		if (getTile(clickPos) == Tile.EMPTY && getHighlighted() == null)
		{
			//can't click a blank square
			return false;
		}
		if (highlightedPos == null)
		{
			//can't highlight other team
			if(!myTeam(getTile(clickPos)))
				return false;
			
			//not clicking a blank square, so select this piece
			highlightTile(clickPos);
		}
		else if (highlightedPos == clickPos)
		{
			//clicking the already highlighted square, so deselect
			redrawTile(clickPos);
			setHighlighted(null);
		}
		else
		{
			GamePiece selectedPiece = getPiece(highlightedPos);
			if (selectedPiece.attemptMove(clickPos))
			{
				movePiece(selectedPiece, highlightedPos);
			}
			//if move fails, redraw this tile
			redrawTile(highlightedPos);
			redrawTile(clickPos);
			setHighlighted(null);
		}
		return true;
	}
	
	private boolean myTeam(Tile tile)
	{
		if (_isHost && tile == Tile.RED)
		{
			return true;
		}
		else if (!_isHost && tile == Tile.BLACK)
		{
			return true;
		}
		return false;
	}
	
	public boolean gameOver()
	{
		if (blackPieces.isEmpty() || redPieces.isEmpty())
		{
			return true;
		}
		else return false;
	}

	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}

	public Move getLastMove() {
		return lastMove;
	}

	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}
}
