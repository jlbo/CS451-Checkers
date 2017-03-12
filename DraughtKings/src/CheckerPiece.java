import java.awt.*;


public class CheckerPiece extends GamePiece {

	public CheckerPiece(Location pos, int width, Color color, GameBoard board) {
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
		
		//the spaces to the right and left diagonal of this
		Location diagRight = diagRight(oldPos);
		Location diagLeft = diagLeft(oldPos);
		
		//If you are moving diagonally right/left and forward 1,
		//(and there's no piece there),
		//CAN move there
		if (newPos.equals(diagRight) || newPos.equals(diagLeft))
		{
			return true;
		}
		
		//the spaces two spaces to the right and left diagonal of a location
		Location diagRight2 = diagRight(diagRight);
		Location diagLeft2 = diagLeft(diagLeft);
		
		//If you are moving diagonally right/left and forward 2,
		//(and there's no piece there),
		//(and there IS an ENEMY piece diagonally right/left and forward 1),
		//can move there
		if (newPos.equals(diagRight2) && enemyAt(diagRight))
		{
			return true;
		}
		
		if (newPos.equals(diagLeft2) && enemyAt(diagLeft))
		{
			return true;
		}
		
		return false;
	}
	
	private Location diagRight(Location from)
	{
		Location diagRight = null;
		if (getColor() == GameBoard.RED_PIECE)
		{
			diagRight = new Location (from.getX() + 1, from.getY() - 1);
		}
		else if (getColor() == GameBoard.BLACK_PIECE)
		{
			diagRight = new Location (from.getX() + 1, from.getY() + 1);
		}
		return diagRight;
	}
	
	private Location diagLeft(Location from)
	{
		Location diagLeft = null;
		if (getColor() == GameBoard.RED_PIECE)
		{
			diagLeft = new Location (from.getX() - 1, from.getY() - 1);
		}
		else if (getColor() == GameBoard.BLACK_PIECE)
		{
			diagLeft = new Location (from.getX() - 1, from.getY() + 1);
		}
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
