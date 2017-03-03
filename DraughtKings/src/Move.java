import java.io.Serializable;


public class Move implements Serializable {
	
	private Tile[][] tiles;
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
}
