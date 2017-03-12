import java.awt.Color;


public enum Tile
{
	EMPTY("empty", Color.WHITE),
	BLACK("black", GameBoard.BLACK_PIECE),
	RED("red", GameBoard.RED_PIECE);

	private final String _name;
	private final Color _color;
	Tile(String name, Color color)
	{
		_name = name;
		_color = color;
	}
	
	public Color getColor()
	{
		return _color;
	}

	public String toString()
	{
		return _name;
	}
}