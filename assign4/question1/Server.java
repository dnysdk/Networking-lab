import java.util.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server {

	/**
	 * @param args
	 */
	
	ArrayList<DataOutputStream> list = new ArrayList<DataOutputStream>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Server s = new Server();
		ServerSocket startConncetion = new ServerSocket(4440);
		while(true){
			Socket inputsocket = startConncetion.accept();
			System.out.println("connected");
			
			Runnable ProcessRequest = new ProcessRequest(inputsocket,s); 		
			new Thread(ProcessRequest).start();		// creating new thread
		}
	}

}
