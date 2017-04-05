import java.util.*;
public class Router {

	ArrayList<Packet> packetlist1 = new ArrayList<Packet>();
	Sink s = new Sink();
	public Packet sendToRouter(Packet p,Router r,int time) {
		// TODO Auto-generated method stub
		
		//packetlist1.add(p);
		//Packet p1 = packetlist1.get(0);
		//packetlist1.remove(0);
		
		//System.out.println("in router");
		//System.out.println(p.tosink);
		Packet p2 = null;
		if(p.tosink==true){
			//packetlist1.remove(0);
			//System.out.println("sending to source");
			p2 = s.sendToSink(p,time,r);
		}
		//System.out.println("going to source");	
		return p2;
		
		//return null;
		
	}
	
}
