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
		_board.redrawTile(e.getPoint());
		super.mouseClicked(e);
	}
}
