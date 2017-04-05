import java.util.ArrayList;
public class Switch {
	// size of array list is fixed so packets drop;
	ArrayList<Packet> packetlist = new ArrayList<Packet>();
	int size;
	Switch(int size){
		this.size = size;
	}
	 boolean sendtoSwitch(Packet p,int timer,int bandwidth) {
		// TODO Auto-generated method stub
		if(packetlist.size()<size){
			packetlist.add(p);
			p.end_time = p.size/bandwidth;
			return true;	
		}
		return false;
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
