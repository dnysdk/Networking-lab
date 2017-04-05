import java.util.ArrayList;
public class Switch {
	
	ArrayList<Packet> packetlist = new ArrayList<Packet>();

	 Packet sendtoSwitch(Packet p,int timer,int bandwidth) {
		// TODO Auto-generated method stub
		packetlist.add(p);
		p.end_time = p.size/bandwidth;
		return p;
	}
	
	 Packet sendtoSink(int timer,int bandwidth){
		if(!packetlist.isEmpty()){
		 Packet p = packetlist.remove(0); // packet sending rate from switch to sink is constant: 1 packet per unit time
		 //time taken by propagation of packet from swtich to sink is packet size/bandwidth
		 
		 p.end_time = p.end_time + timer + p.size/bandwidth;
		 
		 return p;
		}
		else
			return null;
	 }
}
