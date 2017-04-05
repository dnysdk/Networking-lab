import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGui extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	JPanel panel;
	JTextField msg;
	JTextArea chat;
	JButton send;
	DataInputStream dis;
	DataOutputStream dos;
	String username;
	String outputmessage = "";
	ClientGui(){
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
		this.setTitle("Client");	
		send.addActionListener(this);
	}
	
	void printMessage(String message){
		//System.out.println("printing");
		chat.setText(chat.getText() + "\n" +": " + message);
	}
	
//	String sendMessage(String username){
//		this.username = username;
//		//System.out.println(username);
//		
//	}

	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if((event.getSource()== send) && (msg.getText()!="")){
			Client cl = new Client();
			outputmessage = username +": " + msg.getText();
			
			cl.outputdata = outputmessage;
			System.out.println("sacavdca "+msg.getText());
			msg.setText("");
		}
	}

	

}
