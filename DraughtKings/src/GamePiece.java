import java.awt.*;


public abstract class GamePiece {

	private Location _position;
	private int _pieceWidth;
	private Color _color;
	private Tile _tile;
	private GameBoard _board;
	
	public GamePiece(Location pos, int width, Color color, Tile tile, GameBoard board)
	{
		_position = pos;
		_pieceWidth = width;
		_color = color;
		_tile = tile;
		_board = board;
	}
	
	public void move(Location newPos)
	{
		setLocation(newPos);
	}
	
	public abstract boolean checkMove(Location newPos);
	
	public boolean attemptMove(Location newPos)
	{
		if (checkMove(newPos))
		{
			move(newPos);
			return true;
		}
		return false;
	}
	
	public abstract void draw(Graphics2D g);
	
	public Location getLocation()
	{
		return _position;
	}
	
	public void setLocation(Location newLoc)
	{
		_position = newLoc;
	}

	public int getWidth() {
		return _pieceWidth;
	}

	public void setWidth(int _pieceWidth) {
		this._pieceWidth = _pieceWidth;
	}

	public Color getColor() {
		return _color;
	}

	public void setColor(Color _color) {
		this._color = _color;
	}

	public Tile getTile() {
		return _tile;
	}

	public void setTile(Tile _tile) {
		this._tile = _tile;
	}

	public GameBoard getBoard() {
		return _board;
	}
	
}
