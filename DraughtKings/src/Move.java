import java.io.Serializable;


public class Move implements Serializable {
	
	private Tile[][] tiles;
	private Location eliminated;
//	private GamePiece movedPiece;
	private Location from;
	private Location to;
	
	public Move(Tile[][] tiles)
	{
		this.tiles = tiles;
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
	
	public void setTiles(Tile[][] tiles)
	{
		this.tiles = tiles;
	}

	public Location getEliminated() {
		return eliminated;
	}

	public void setEliminated(Location eliminated) {
		this.eliminated = eliminated;
	}

//	public GamePiece getMovedPiece() {
//		return movedPiece;
//	}
//
//	public void setMovedPiece(GamePiece movedPiece) {
//		this.movedPiece = movedPiece;
//	}

	public Location getFrom() {
		return from;
	}

	public void setFrom(Location from) {
		this.from = from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}
}
