import java.util.ArrayList;
public class Switch {
	// size of array list is fixed so packets drop;
	ArrayList<Packet> packetlist = new ArrayList<Packet>();
	int size;
	double utilization;
	Switch(int size){
		this.size = size;
		utilization = 0;
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
		double capacity = (double)bandwidth;
	 	while(capacity!=0){
	 		if(!packetlist.isEmpty()){
		 	
			 Packet p = packetlist.get(0); // packet sending rate from switch to sink is constant: 1 packet per unit time
			 //time taken by propagation of packet from swtich to sink is packet size/bandwidth
			 p.send = timer;
			 if(p.size<=capacity){
			 	capacity = capacity - p.size;
			 	packetlist.remove(0);
			 	//System.out.println("removed" + packetlist.size());
			 	//System.out.println(capacity);
			 	utilization += p.size;
			 	p.end_time = p.end_time + timer + p.size/bandwidth;
			 }else if(p.size>capacity){
			 	p.size = p.size - capacity;
			 	utilization += capacity;
			 	capacity = 0;
			 }
			 
			 
			 //return p;
			}
			else
				break;

	 	}
		return null;
	 }
}
