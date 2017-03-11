import java.awt.*;


public class CheckerPiece extends GamePiece {

	public CheckerPiece(Location pos, int width, Color color, GameBoard board) {
		super(pos, width, color, board);
	}

	@Override
	public boolean checkMove(Location newPos) {
		// TODO Auto-generated method stub
		//check rules
		//Use GameBoard board = getBoard() for other tiles
		//if color is BLACK, then moving in direction of INCREASE Y
		//if color is RED, then moving in direction of DECREASE Y
		return true;
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
