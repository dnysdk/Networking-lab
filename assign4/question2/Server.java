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
	static ArrayList<String> onlineip = new ArrayList<String>();
	static ArrayList<String> logininfo = new ArrayList<String>();
	ArrayList<DataOutputStream> list = new ArrayList<DataOutputStream>();
	static int serverport ;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String fileName = "logininfo.txt";
		FileInputStream filedata = null;
		filedata = new FileInputStream(fileName);
		BufferedReader fp = new BufferedReader(new InputStreamReader(filedata));	//reding configuration file
		String line;
		//serverport = Integer.parseInt(fp.readLine());
		serverport = 4444;
		while ((line = fp.readLine()) != null) {
		       logininfo.add(line);
		}

		Server s = new Server();
		ServerSocket startConncetion = new ServerSocket(4444);
		while(true){
			Socket inputsocket = startConncetion.accept();
			System.out.println("connected");
			String remoteip = ""+inputsocket.getRemoteSocketAddress();
			int cutindex = remoteip.indexOf(':');
			remoteip = remoteip.substring(1,cutindex);
			onlineip.add(remoteip);
			Runnable ProcessRequest = new ProcessRequest(inputsocket,s,remoteip); 		
			new Thread(ProcessRequest).start();		// creating new thread
		}
		
		
	}

}
