
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