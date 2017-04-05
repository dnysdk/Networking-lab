import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame implements ActionListener {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	static String string="";
	static String outputdata="";
	static String username;
	static JPanel panel;
	static JTextField msg;
	static JTextArea chat;
	static JButton send;
	static DataInputStream dis;
	static DataOutputStream dos;
	static String outputmessage = "";
	static Socket connection;
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
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if((event.getSource()== send) && (msg.getText()!="")){
			try {
				DataOutputStream out= new DataOutputStream(connection.getOutputStream());
				outputmessage = username +": " + msg.getText();
				System.out.println(outputmessage);
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
		username = args[0];
		//gui.username = username;
		System.out.println(username);
		String ip = "172.16.180.27";
		int port = 4440;
		connection = new Socket(ip,port);
		
		Client cl = new Client();
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			
			try {
				//System.out.println(outputdata);
				DataInputStream in = new DataInputStream(connection.getInputStream());
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				string = inFromServer.readLine();
				System.out.println("abcd");
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

}
