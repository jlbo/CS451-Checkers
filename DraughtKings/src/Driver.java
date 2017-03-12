import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;


public class Driver {

	public void exitGame(){
		System.exit(0);
	}
	
	// Use this for hosting a game
	public static NetworkManager hostGame() throws IOException { 
		NetworkManager nm = new NetworkManager();
		return nm;
	}
	
	// Use this when joining a game, Give it the Host's IP address as a String
	public static NetworkManager joinGame(String IP) throws IOException {
		NetworkManager nm = new NetworkManager(IP);
		return nm;
	}
	
	public static void main(String[] args) {
		final GameManager[] gm = {null};
		final NetworkManager[] nm = {null};
		
		
		final String[] IP = {""};
		final JFrame MenuFrame = new JFrame("Draught Kings Launcher");
		final JFrame GameFrame = new JFrame("Draught Kings");
		final JPanel controlPanel = new JPanel();
		final JPanel JoinPanel = new JPanel();
		final JLabel header;
		final JButton Host = new JButton("Host Game");
		final JButton Join = new JButton("Join Game");
		final JButton Quit = new JButton("Quit");
		final JButton Connect = new JButton("Connect");
		
		MenuFrame.setSize(200, 100);
		
		header = new JLabel("Welcome to Draught Kings", JLabel.CENTER);
		
		//listeners for button functionality
		Host.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Host game
				try {
//					JLabel joiningMessage = new JLabel("Waiting for player to join...");
//					JoinPanel.add(joiningMessage);
//					MenuFrame.add(JoinPanel, BorderLayout.SOUTH);
//					MenuFrame.setVisible(true);
					
					nm[0] = hostGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gm[0] = new GameManager(nm[0]);
				gm[0].start(GameFrame);	
			}
		});
		
		Join.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//new frame with text box to set IP address to join
				JoinPanel.setLayout(new FlowLayout());
				final JTextField IPField = new JTextField(20);
				
				JoinPanel.add(IPField);
				JoinPanel.add(Connect);
				
				MenuFrame.add(JoinPanel, BorderLayout.SOUTH);
				MenuFrame.setVisible(true);
				
				Connect.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//Connect to game
						IP[0] = IPField.getText();
						JLabel message = new JLabel("Connecting to " + IP[0] + "...");
						JoinPanel.remove(IPField);
						JoinPanel.remove(Connect);
						JoinPanel.add(message);
						MenuFrame.setVisible(true);
						
						try {
							nm[0] = joinGame(IP[0]);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						gm[0] = new GameManager(nm[0]);
						gm[0].start(GameFrame);
					}
				});
				
			}
		});
		
		Quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		
		
		//Display main window
		MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//MenuFrame.pack();
		MenuFrame.setSize(MenuFrame.getWidth() + 400, MenuFrame.getHeight() + 400);
		
		controlPanel.setLayout(new FlowLayout());
		MenuFrame.add(header, BorderLayout.NORTH);
		MenuFrame.add(controlPanel, BorderLayout.CENTER);

		controlPanel.setSize(400, 100);
		controlPanel.add(Host, BorderLayout.NORTH);
		controlPanel.add(Join, BorderLayout.CENTER);
		controlPanel.add(Quit, BorderLayout.SOUTH);
		
		
		MenuFrame.setVisible(true);
		
		
//		if (true) { /*clicks host game*/
//			try {
//				nm = hostGame();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else if (true) { /*clicks join game*/
//			String ipaddr = null;//get IP address here
//			try {
//				nm= joinGame(ipaddr);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
//		boolean host = true;
//		if (host) {
//			try {
//				nm = hostGame();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			try {
//				nm = joinGame("localhost");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}
