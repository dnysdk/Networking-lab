import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class ProcessRequest implements Runnable {
	Socket inputsocket;
	Server s;
	DataOutputStream outputdata;
	String ip;
	ProcessRequest(Socket inputsocket,Server s,String ip){
		this.inputsocket = inputsocket;
		this.s = s;
		this.ip = ip; 
	}
	BufferedWriter bw = null;
	FileWriter fw = null;
	
	public void run() {
		// TODO Auto-generated method stub
		
		try{
			DataInputStream inputdata = new DataInputStream(inputsocket.getInputStream());
			outputdata = new DataOutputStream(inputsocket.getOutputStream());
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(inputsocket.getInputStream()));
			//DataOutputStream outToClient = new DataOutputStream(inputsocket.getOutputStream());
			s.list.add(outputdata);
			System.out.println(s.list.size());
			while(true){
				
				String input = inFromClient.readLine();
				System.out.println(input);
				String info[] = input.split(",");
				String output = "false";
				boolean flag = false;
				if(info[0].equals("login")){
					for(String line : s.logininfo){
						String check[] = line.split(",");
						if(info[1].equals(check[0])&&info[2].equals(check[1])){
							flag = true;
							//output = "true" + "," + check[2] + "\n";
							break;
						}
					}
					if(flag==false){
						output = "false\n";
					}
					if(flag==true){
						output = "true";
						for(String line : s.logininfo){
							System.out.println(line);
							String check[] = line.split(",");
							if(s.onlineip.contains(check[2]) && check[0].equals(info[1])==false){
								output = output + "," + check[0] + "," + check[2]+","+check[3];
							}
						}
						output = output + "\n";
					}
					//System.out.println(output);
					outputdata.writeBytes(output);
				}else if(info[0].equals("register")){
					File file = new File("logininfo.txt");
					fw = new FileWriter(file.getAbsoluteFile(),true);
					bw = new BufferedWriter(fw);
					String port = Integer.toString(s.serverport + 1);
					s.serverport += 1; 
					String content = info[1] + "," + info[2] + "," + ip+","+port+"\n";
					System.out.println(content);
					bw.write(content);
					bw.close();
					fw.close();
					outputdata.writeBytes("true\n");
				}else{
					if(input!=""){
						for(DataOutputStream out : s.list){
							System.out.println("one");
							out.writeBytes(input+"\n");
						}
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


