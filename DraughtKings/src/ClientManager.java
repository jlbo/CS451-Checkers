import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientManager implements NetworkManager
{
	Socket clientSocket;
	InetAddress IPaddr;
	int port;
	PrintWriter out;
	BufferedReader in;
	public ClientManager(String IP, int port) throws IOException {
		
		try {
			IPaddr = InetAddress.getByName(IP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.port = port;
		
		clientSocket = new Socket(IPaddr, port);
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    
	}
	
	@Override
	public void sendMove(String move) {
		out.println(move);
		
		// get the ok
		String gotit = "";
		try {
			gotit = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!new String("'ok").contentEquals(gotit)) {
			// something went wrong
		}
	}
	
	@Override
	public String readMove() {
		String move = "";
		try {
			move = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return move;
		
	}
	
	@Override
	public void sendAck() {
		sendMove("'ok");
	}
	
}
