import java.io.Serializable;


public class Move implements Serializable {
	
	private Tile[][] tiles;
	
	public Move(Tile[][] tiles)
	{
		this.tiles = tiles;
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
}
