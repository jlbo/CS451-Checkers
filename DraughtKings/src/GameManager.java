
public class GameManager 
{
	NetworkManager nm;
	Boolean playerTurn;
	public GameManager(NetworkManager nm) {
		this.nm = nm;
		playerTurn = nm.isHost;
	}
	
	
}
