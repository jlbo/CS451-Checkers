import java.awt.*;


public class CheckerPiece extends GamePiece {

	public CheckerPiece(Location pos, int width, Color color) {
		super(pos, width, color);
	}

	@Override
	public boolean checkMove(Location newPos) {
		// TODO Auto-generated method stub
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
