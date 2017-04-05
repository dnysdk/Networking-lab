import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class ProcessRequest implements Runnable {
	Socket inputsocket;
	Server s;
	DataOutputStream outputdata;
	ProcessRequest(Socket inputsocket,Server s){
		this.inputsocket = inputsocket;
		this.s = s;
	}
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			DataInputStream inputdata = new DataInputStream(inputsocket.getInputStream());
			outputdata = new DataOutputStream(inputsocket.getOutputStream());
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(inputsocket.getInputStream()));
			//DataOutputStream  outToClient = new DataOutputStream(inputsocket.getOutputStream());
			s.list.add(outputdata);
			System.out.println(s.list.size());
			while(true){
				
				String input = inFromClient.readLine();
				if(input!="")
					System.out.println("input to server : " + input);
				//outputdata.writeBytes(input+"\n");
				
				if(input!=""){
					for(DataOutputStream out : s.list){
						System.out.println("one");
						out.writeBytes(input+"\n");
					}
				}
				
			}		
			
		}catch(Exception E){
			int i=0;
			for(DataOutputStream out : s.list){
				if(out == outputdata){
					s.list.remove(i);
					break;
				}
				i++;
			}
			System.out.println("Connection close");
		}
		
	}
	
}


