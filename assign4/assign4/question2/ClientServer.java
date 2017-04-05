import java.io.*;
import java.net.*;


public class ClientServer implements Runnable{
	ServerSocket connectclient;
	Socket inputsocket;
	//Server s;
	DataOutputStream outputdata;
	int port;
	Client cl;
	int flag=0;
	ClientServer(ServerSocket connectclient){
		this.connectclient = connectclient;
	}
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("in run");
		try {
			inputsocket = connectclient.accept();
			flag=1;
			cl = new Client();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			
			DataInputStream inputdata = new DataInputStream(inputsocket.getInputStream());
			outputdata = new DataOutputStream(inputsocket.getOutputStream());
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(inputsocket.getInputStream()));
			//DataOutputStream  outToClient = new DataOutputStream(inputsocket.getOutputStream());
			//s.list.add(outputdata);
			//System.out.println(s.list.size());
			while(true){
				
				String input = inFromClient.readLine();
				if(input!="")
					System.out.println("input to server : " + input);
				if(flag==1)
					cl.printMessage(input);
				outputdata.writeBytes(input+"\n");
				//out.writeBytes
//				if(input!=""){
//					for(DataOutputStream out : s.list){
//						//System.out.println("one");
//						out.writeBytes(input+"\n");
//					}
//				}
				
			}
			
			
			
			
			
		}catch(Exception E){
//			int i=0;
//			for(DataOutputStream out : s.list){
//				if(out == outputdata){
//					s.list.remove(i);
//					break;
//				}
//				i++;
//			}
			System.out.println("Connection close");
		}
	}

}
