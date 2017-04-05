import java.util.*;
import java.lang.*;
public class Router {

	ArrayList<Packet> packetlist;
	int size;  //size of list
	Sink s;
	int r1,r2;	
	Router(int r1,int r2){
		size = 40;
		packetlist = new ArrayList<Packet>();
		this.r1 = r1;
		this.r2 = r2;
		s = new Sink();
	}
	public Packet sendToRouter(Packet p,Router r,int time) {
		
		if(packetlist.size()<size){
			packetlist.add(p);
			p.isack = true;	
		}
		return p;
	}

	public void sending(Source s1) {
		int count1 = 0;
		int count2 = 0;
		// System.out.println("list size in router : "+packetlist.size());	
		for(int m=0;m<packetlist.size();m++){
			
			Packet p = packetlist.get(m);
			if(count1<r1 && p.hostid==1){
				packetlist.remove(m);
				m--;
				Packet g = s.send(p);
				
				int l = 0;
				for(Packet k : s1.packetList1){
					if(k.packetid==g.packetid){
						k.end_time = s1.time + 1;
						s1.delay1 = s1.delay1 + k.end_time - k.send_time;
						s1.packetList1.remove(l);
						
						s1.countSend1++;
						
			
						// System.out.println("increase1");
						break;
					}
					l++;
				}
				count1++;
			}else if(count2<r2 && p.hostid==2){
				packetlist.remove(m);
				m--;
				Packet g = s.send(p);
				
				int l = 0;
				for(Packet k : s1.packetList2){
					if(k.packetid==g.packetid){
						k.end_time = s1.time + 1;
						s1.delay2 = s1.delay2 + k.end_time - k.send_time;
						// s1.i--;
						s1.packetList2.remove(l);
						l--;
						s1.countSend2++;
						//s1.countsize2++;
						//if(s1.countsize2==s1.windowsize2){
						//	s1.windowsize2 = s1.windowsize2 + 1;
						//	s1.countsize2=0;
						//}
						
						// s1.windowsize = s1.windowsize + 1;
						// System.out.println("increase2");
						break;
					}
					l++;
				}
				count2++;
			}	
			
		}
		// System.out.println("count");
		// System.out.println(count1);
		// System.out.println(count2);
	}
}
