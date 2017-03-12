import java.awt.Color;
import java.awt.Graphics2D;

public class KingPiece extends GamePiece {
	public KingPiece(Location pos, int width, Color color, GameBoard board) {
		super(pos, width, color, board);
	}

	@Override
	public boolean checkMove(Location newPos) {
		
		GameBoard board = getBoard();
		//if there is another piece at the position, can't move there
		if (board.getTile(newPos) != Tile.EMPTY)
		{
			return false;
		}
		
		
		Location oldPos = getLocation();
		
		//the spaces to the upper/downer right and upper/downer left diagonal of this
		Location diagUpRight = diagUpRight(oldPos);
		Location diagUpLeft = diagUpLeft(oldPos);
		Location diagDownRight = diagDownRight(oldPos);
		Location diagDownLeft = diagDownLeft(oldPos);
		
		//If you are moving diagonally right/left and forward or backward 1,
		//(and there's no piece there),
		//CAN move there
		if (newPos.equals(diagUpRight) || newPos.equals(diagUpLeft) 
				|| newPos.equals(diagDownRight) || newPos.equals(diagDownLeft))
		{
			return true;
		}
		
		//the spaces two spaces to the upper/downer right and left diagonal of a location
		Location diagUpRight2 = diagUpRight(diagUpRight);
		Location diagUpLeft2 = diagUpLeft(diagUpLeft);
		Location diagDownRight2 = diagUpRight(diagDownRight);
		Location diagDownLeft2 = diagDownLeft(diagDownLeft);
		
		//If you are moving diagonally right/left and forward or downward 2,
		//(and there's no piece there),
		//(and there IS an ENEMY piece diagonally right/left and forward 1),
		//can move there
		if (newPos.equals(diagUpRight2) && enemyAt(diagUpRight))
		{
			return true;
		}
		
		if (newPos.equals(diagUpLeft2) && enemyAt(diagUpLeft))
		{
			return true;
		}
		
		if (newPos.equals(diagDownRight2) && enemyAt(diagDownRight))
		{
			return true;
		}
		
		if (newPos.equals(diagDownLeft2) && enemyAt(diagDownLeft))
		{
			return true;
		}
		
		return false;
	}
	
	private Location diagUpRight(Location from)
	{
		Location diagRight = null;
		diagRight = new Location (from.getX() + 1, from.getY() - 1);
		return diagRight;
	}
	
	private Location diagDownRight(Location from)
	{
		Location diagRight = null;
		diagRight = new Location (from.getX() + 1, from.getY() + 1);
		return diagRight;
	}
	
	private Location diagUpLeft(Location from)
	{
		Location diagLeft = null;
		diagLeft = new Location (from.getX() - 1, from.getY() - 1);
		return diagLeft;
	}
	
	private Location diagDownLeft(Location from)
	{
		Location diagLeft = null;
		diagLeft = new Location (from.getX() - 1, from.getY() + 1);
		return diagLeft;
	}
	
	
	
	private boolean enemyAt(Location loc)
	{
		Tile tileAt = getBoard().getTile(loc);
		if (tileAt != Tile.EMPTY)
		{
			//if the tile's color is not the same as this piece's color,
			//return TRUE, as in, 'there IS an ENEMY AT loc'
			return tileAt.getColor() != getColor();
		}
		return false;
	}


	// this needs to be changed
	@Override
	public void draw(Graphics2D g) {
		Location loc = getLocation();
		Color oldColor = g.getColor();
		g.setColor(getColor());
		g.fillArc(loc.getX() * getWidth(),loc.getY() * getWidth(), getWidth(), getWidth(), 0, 360);
		//revert graphics
		g.setColor(oldColor);
	}
}
