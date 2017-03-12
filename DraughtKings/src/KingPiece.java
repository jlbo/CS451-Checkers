import java.awt.Color;
import java.awt.Graphics2D;

public class KingPiece extends GamePiece {
	public KingPiece(Location pos, int width, Color color, Tile tile, GameBoard board) {
		super(pos, width, color, tile, board);
	}

	@Override
	public boolean checkMove(Location newPos) {
		// TODO Auto-generated method stub
		//check rules
		//Use GameBoard board = getBoard() for other tiles
		//if color is BLACK, then moving in direction of INCREASE Y or decrease
		//if color is RED, then moving in direction of DECREASE Y or increase
		return true;
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
