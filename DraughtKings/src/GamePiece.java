import java.awt.*;


public abstract class GamePiece {

	private Location _position;
	private int _pieceWidth;
	private Color _color;
	
	public GamePiece(Location pos, int width, Color color)
	{
		_position = pos;
		_pieceWidth = width;
		_color = color;
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
	
}
