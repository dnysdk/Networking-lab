import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class ProcessRequest {
	Socket inputsocket;
	ProcessRequest(Socket inputsocket){
		this.inputsocket = inputsocket;
	}
	public void process() throws Exception {
		// TODO Auto-generated method stub
		DataInputStream inputdata = new DataInputStream(inputsocket.getInputStream());
		OutputStream output = inputsocket.getOutputStream();
		 BufferedReader d = new BufferedReader(new InputStreamReader(inputdata));
		BufferedOutputStream out = new BufferedOutputStream(output);
		//String request = d.readLine();
		String request=inputdata.readLine().trim(); 		//getting first line of request containing file name
		System.out.println(request);
		StringTokenizer st=new StringTokenizer(request);		//tockinze request
		String header=st.nextToken(); 			
		System.out.println(header);
		if(header.equals("GET"))
		{
			String name=st.nextToken();
			int len=name.length();
			String fileName=name.substring(1,len);		//getting file name from request
			System.out.println(fileName);
			FileInputStream file=null;
			boolean isfile=true;
			if(fileName.equals(""))
			fileName="default.html";		// setting default page
			try
			{file=new FileInputStream(fileName);
			}
			catch(Exception ex) {
				
			isfile=false;
			file = new FileInputStream("404.html"); 			
			}
			String ServerLine="Simple HTTP Server";
			String StatusLine=null;
			String ContentTypeLine=null;
			String ContentLengthLine=null;
			String ContentBody=null;
			if (isfile) {
				   StatusLine = "HTTP/1.0 200 OK" + "\r\n";
				   //get content type by extension
				   ContentTypeLine = "Content-type: html/text  \r\n";
				   ContentLengthLine = "Content-Length: " + (new Integer(file.available())).toString() + "\r\n";
				} else {
				  StatusLine = "HTTP/1.0 404 Not Found\r\n";
				  ContentTypeLine = "Content-type: html/text  \r\n";
				  ContentLengthLine = "Content-Length: " + (new Integer(file.available())).toString() + "\r\n";
				}
				// writing HTTP responce
				output.write(StatusLine.getBytes());
				output.write(ServerLine.getBytes());
				output.write(ContentTypeLine.getBytes());
				output.write(ContentLengthLine.getBytes());
				output.write("\r\n".getBytes());
				byte[] buffer = new byte[1024];
				 int bytes = 0;

				   while ((bytes = file.read(buffer)) != -1) {
				     output.write(buffer, 0, bytes);
				   }
				   Thread.sleep(10000);
//				if (isfile) {
//					   byte[] buffer = new byte[1024];
//					   int bytes = 0;
//
//					   while ((bytes = file.read(buffer)) != -1) {
//					     output.write(buffer, 0, bytes);
//					   }
//					} else {
//						System.out.println(ContentBody);
//					   output.write(ContentBody.getBytes());
//					}
		}
	}
	
}

