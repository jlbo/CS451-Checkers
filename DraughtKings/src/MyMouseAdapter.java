import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyMouseAdapter extends MouseAdapter {

	private GameBoard _board;
	public MyMouseAdapter(GameBoard board) {
		super();
		_board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Location loc = _board.locFromPoint(e.getPoint());
		_board.selectPiece(loc);
//		if (_board.getHighlighted() == null)
//		{
//			_board.highlightTile(loc);
//		}
//		else if (_board.getHighlighted() == loc)
//		{
//			_board.redrawTile(loc);
//			_board.setHighlighted(null);
//		}
//		else
//		{
//			//check move between getHighlighted and e.getPoint
//			//if move fails, redraw this tile
//			_board.redrawTile(_board.getHighlighted());
//			_board.setHighlighted(null);
//		}
//		_board.redrawTile(e.getPoint());
		super.mouseClicked(e);
	}
	
}
