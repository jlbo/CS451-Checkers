import java.awt.Point;


public class Location {

	private int _x;
	private int _y;
	
	public Location(int x, int y)
	{
		_x = x;
		_y = y;
	}
	
	public Location (Point p, int tileWidth)
	{
		_x = p.x / tileWidth;
		_y = p.y / tileWidth;
	}
	
	public int getX()
	{
		return _x;
	}
	
	public int getY()
	{
		return _y;
	}

	@Override
	public boolean equals(Object obj) {
		Location otherLoc = (Location) obj;
		return otherLoc.getX() == getX() && otherLoc.getY() == getY();
//		if (obj instanceof Location)
//		{
//			Location otherLoc = (Location) obj;
//			return otherLoc.getX() == getX() && otherLoc.getY() == getY();
//		}
//		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getX() * getY();
	}

	@Override
	public String toString() {
		return "Location: (" + getX() + ", " + getY() + ")";
	}
	
}
