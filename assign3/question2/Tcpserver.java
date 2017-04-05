import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.*;
import java.lang.*;
import java.util.*;  


public class Tcpserver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String fileName = "config.txt"; 		// configuration file
		FileInputStream filedata = null;
		filedata = new FileInputStream(fileName);
		BufferedReader fp = new BufferedReader(new InputStreamReader(filedata));	//reding configuration file
		String line;
		String defaultFile = "";
		String blockedIp = "";
		String maxConnections = "";
		line = fp.readLine();
		int index = line.indexOf('=');
		index = index + 2;
		maxConnections = line.substring(index);	
		line = fp.readLine();
		index = line.indexOf('=');
		index = index + 2;
		blockedIp = line.substring(index);
		line = fp.readLine();
		index = line.indexOf('=');
		index = index + 2;
		defaultFile = line.substring(index);
		String blockedIps[] = blockedIp.split(","); // storing all blocked ips into array
		ServerSocket startConncetion = new ServerSocket(4441);
		while(true){
			Socket inputsocket = startConncetion.accept();
			String remoteip = ""+inputsocket.getRemoteSocketAddress();
			int cutindex = remoteip.indexOf(':');
			//System.out.println(cutindex);
			//System.out.println(remoteip);
			remoteip = remoteip.substring(1,cutindex);
			int flag = 1;
			for(String ip : blockedIps){		// checking weather ip is blocked or not
				if(remoteip.equals(ip)){
					flag=0;
					break;
				}
			}
			if(flag==0){
				System.out.println(remoteip +" ip is blocked");
				inputsocket.close();		//closing socket if ip is blocked
				continue;
			}
			//System.out.println(Integer.parseInt(maxConnections));
			if(Thread.activeCount()-1==Integer.parseInt(maxConnections)){
				System.out.println("maxConnections limit exceds");
				inputsocket.close(); 		// closing socket if maximum connection limit exceds
				continue;
			}
			System.out.println("number of active threads :- "+Thread.activeCount());
			System.out.println("Connection request accepted");
			Runnable ProcessRequest = new ProcessRequest(inputsocket,defaultFile); 		
			new Thread(ProcessRequest).start();		// creating new thread 
			
		}
	}
}
