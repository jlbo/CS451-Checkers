import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkManager
{
	Socket clientSocket;
	ServerSocket serverSocket;
	
	InetAddress IPaddr;
	int port;
	PrintWriter out;
	BufferedReader in;
	
	
	// Constructor for Clients - connects to host, sets up input and output
	// Expected Args: IP address::String, port number::Int
	public NetworkManager(String IP, int port) throws IOException {
		
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
	
	//Constructor for Host - creates host, connects to client, sets up input and output
	// Expected Args: port number::Int
	public NetworkManager(int port) throws IOException
	{	
		this.port = port;
		
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  
	}
	
	
	// sendMove: sends the move to socket
	// Inputs: move::String in format of "move # #"
	// Outputs: Boolean - if send fails return false otherwise return true
	public Boolean sendMove(String move) {
		
		out.println(move);
		
		// get the ok
		String gotit = "";
		try {
			gotit = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		if (!new String("'ok").contentEquals(gotit)) {
			// something went wrong
			return false;
		}
		
		return true;
	}
	
	//readMove() - reads Move from socket
	//Output - String, if success String of type "move # #", if failure empty String
	public String readMove() {
		String move = "";
		try {
			move = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//we got it
		sendAck();
		return move;
		
	}
	
	//sendAck()
	//sends move to socket, confirms we got the move
	public void sendAck() {
		sendMove("'ok");
	}
	
}
