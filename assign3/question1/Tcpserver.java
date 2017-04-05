import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.*;


public class Tcpserver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ServerSocket startConncetion = new ServerSocket(9876);  
		while(true){
			Socket inputsocket = startConncetion.accept();  //accepting connection
			System.out.println(inputsocket.getRemoteSocketAddress());		// this is for ip of client
			ProcessRequest request = new ProcessRequest(inputsocket);		
			request.process();		// calling process function
		}
	}

}
