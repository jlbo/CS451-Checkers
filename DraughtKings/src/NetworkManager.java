import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	int port = 12000; // this is the port were using for now
	Boolean isHost;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	
	// Constructor for Clients - connects to host, sets up input and output
	// Expected Args: IP address::String, port number::Int
	public NetworkManager(String IP) throws IOException {
		
		try {
			IPaddr = InetAddress.getByName(IP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		clientSocket = new Socket(IPaddr, port);
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		out.flush();
		
		this.isHost = false;
		    
	}
	
	//Constructor for Host - creates host, connects to client, sets up input and output
	// Expected Args: port number::Int
	public NetworkManager() throws IOException
	{	
		
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		clientSocket.setSoTimeout(5000);
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		out.flush();
		IPaddr = serverSocket.getInetAddress(); // getting host IP
		this.isHost = true;
		
		  
	}
	
	
	// sendMove: sends the move to socket
	// Inputs: move::String in format of "move # #"
	// Outputs: Boolean - if send fails return false otherwise return true
	public Boolean sendMove(Move move) {
		try {
			out.writeObject(move);
			String ok = (String)in.readObject();
			if(ok.equals("ok")){
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	//readMove() - reads Move from socket
	//Output - String, if success String of type "move # #", if failure empty String
	public Move readMove() {
		
		try {
			Move move = (Move)in.readObject();
			sendAck();
			return move;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//sendAck()
	//sends move to socket, confirms we got the move
	public void sendAck() {
		try {
			out.writeObject("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean readAck() {
		try {
			String ok = (String) in.readObject();
			if (ok.equalsIgnoreCase("ok"))
				return true;
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
	public String getHostIPString()
	{
		return IPaddr.getHostAddress();
	}
	
}
