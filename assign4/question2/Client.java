import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame implements ActionListener {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	static Socket connectfriend;
	static String string="";
	static String outputdata="";
	static String username = null;
	static JPanel panel;
	static JPanel loginpanel;
	static JFrame frame;
	static JTextField msg;
	static JTextArea chat;
	static JButton send;
	static DataInputStream dis;
	static DataOutputStream dos;
	static String outputmessage = "";
	static Socket connection;
	static JTextField userText;
	static JPasswordField passwordText;
	static JButton loginButton;
	static JButton registerButton;
	static String password = null;
	static boolean loginflag = false;
	static boolean registerflag=false;
	static String friend,friendsip,friendsport,ownport;
	static String newusername;
	static String newpassword;
	static boolean talk = false;
	static ClientServer cli;
	Client(){
		panel = new JPanel();
		msg = new JTextField();
		chat = new JTextArea();
		send = new JButton("Send");
		this.setSize(500,500);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		this.add(panel);
		chat.setBounds(20, 20, 450, 360);
		panel.add(chat);
		msg.setBounds(20, 400, 340, 30);
		panel.add(msg);
		send.setBounds(375, 400, 95, 30);
		panel.add(send);
		this.setTitle("Client : "+username);	
		send.addActionListener(this);
	}
	Client(String login){
		frame = new JFrame("Login portal");
		frame.setSize(300,150);
		loginpanel = new JPanel();
		frame.add(loginpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginpanel.setLayout(null);
		JLabel userLabel= new JLabel("Username");
		userLabel.setBounds(10, 10, 100, 25);
		loginpanel.add(userLabel);
		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		loginpanel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		loginpanel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		loginpanel.add(passwordText);

		loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		loginpanel.add(loginButton);
		registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 100, 25);
		loginpanel.add(registerButton);
		loginButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {  
			username = userText.getText();
			password = new String(passwordText.getPassword());
			System.out.println(username);
			System.out.println(password);
			loginflag = true;
		   frame.dispose();
		   //cl.setVisible(true);
		  }
		});
		registerButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			newusername = userText.getText();
			newpassword = new String(passwordText.getPassword());
//			System.out.println(username);
//			System.out.println(password);
			registerflag = true;
		   frame.dispose();
		   //cl.setVisible(true);
		  }
		});
		frame.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if((event.getSource()== send) && (msg.getText()!="")){
			try {
				//ClientServer cli = new ClientServer();
				DataOutputStream out;
				outputmessage = username +": " + msg.getText();
				if(cli.flag==1){
					out= new DataOutputStream(cli.inputsocket.getOutputStream());
					chat.setText(chat.getText() + "\n" +": " + outputmessage);
				}
				else
					out= new DataOutputStream(connectfriend.getOutputStream());
				
				//System.out.println(outputmessage);
				
				out.writeBytes(outputmessage+"\n");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			msg.setText("");
		}
		
	}
	static void printMessage(String message){
		//System.out.println("printing");
		chat.setText(chat.getText() + "\n" +": " + message);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException  {
		// TODO Auto-generated method stub
		//ClientGui gui = new ClientGui();
		//username = args[0];
		//gui.username = username;
		//System.out.println(username);
		String ip = "172.16.152.250";
		int port = 4444;
		
		ServerSocket connectclient = new ServerSocket(4446);	
		cli = new ClientServer(connectclient); 		
		Thread t = new Thread(cli);
		t.start();
		Client login = new Client("login");
		//System.out.println(username);
		//System.out.println(password);
		//boolean flag = false;
		while(loginflag==false && registerflag==false){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("asaf");
		connection = new Socket(ip,port);
		while(true){
			DataInputStream input = new DataInputStream(connection.getInputStream());
			BufferedReader checkinfo = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			DataOutputStream output= new DataOutputStream(connection.getOutputStream());
			String checkmessage="";
			if(loginflag==true){
				checkmessage = "login,"+username+","+password+"\n";
			}else if(registerflag==true){
				username = newusername;
				password = newpassword;
				checkmessage = "register,"+username+","+password+"\n";
			}
			//System.out.println(checkmessage);
			output.writeBytes(checkmessage);
			String check = checkinfo.readLine();
			//System.out.println("before");
			System.out.println(check);
			String info[] = check.split(",");
			if(info[0].equals("true")){
				if(loginflag==true){
					System.out.println("Online Friends with their usernames :");
					for(int i=1;i< info.length;i++){
						System.out.println(info[i]);
						i++;
						i++;
					}
					System.out.println("\nEnter the username to whom u want to chat : ");
					System.out.print("Enter the username : ");
					Scanner in = new Scanner(System.in);
					friend = in.nextLine();
					for(int i=1;i< info.length;i++){
						if(info[i].equals(friend)){
							friendsip = info[i+1];
							friendsport = info[i+2];
							break;
						}
						i++;
					}
					talk = true;
				}else if(registerflag==true){
					JOptionPane.showMessageDialog(null, "Registered successfully");
				}
				break;
			}else{
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				break;
			}
		}
		
		//if(talk==true){
		
		if(talk==true){
			Client cl = new Client();
			connectfriend = new Socket(friendsip,Integer.parseInt(friendsport));
			while(true){
				try {
					//System.out.println(outputdata);
					DataInputStream in = new DataInputStream(connectfriend.getInputStream());
					BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connectfriend.getInputStream()));
					
					string = inFromServer.readLine();
					System.out.println(string);
					if(string!="")
						printMessage(string);
					//System.out.println("after");
					
					//gui.sendMessage(username);
					
				} catch (Exception e1) {
					printMessage("Message sending fail ");
					try {
						Thread.sleep(3000);
						System.exit(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	//Client cl = new Client();
		//}
		
		
//		while (true) {
//			
//			try {
//				//System.out.println(outputdata);
//				DataInputStream in = new DataInputStream(connection.getInputStream());
//				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				
//				string = inFromServer.readLine();
//				//System.out.println("abcd");
//				if(string!="")
//					printMessage(string);
//				//System.out.println("after");
//				
//				//gui.sendMessage(username);
//				
//			} catch (Exception e1) {
//				printMessage("Message sending fail ");
//				try {
//					Thread.sleep(3000);
//					System.exit(0);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
	}

}
