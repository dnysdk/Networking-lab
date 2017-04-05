import java.util.*;

public class Source {
	int id;
	double lasttime;
	int nop;//number of available packets
	double delay;
	int sendpackets;
	int bandwidth;
	int dropedpackets;
	ArrayList<Packet> plist = new ArrayList<Packet>();
	Source(int id,int bandwidth){
		this.id = id;
		this.lasttime=0;
		this.nop=0;
		this.delay=0;
		sendpackets = 0;
		dropedpackets = 0;
		this.bandwidth=bandwidth;
	}
	boolean generatePacket(int time,int size,double lambda){
		int i = (int)(lasttime + 1);
		while(lasttime < i){
			double random = Math.random();
			random = 1-random;
			double result = (-1*Math.log(random))/lambda;
			//System.out.println(result);
			lasttime = lasttime + result;
			double stime = lasttime + size/bandwidth;
			Packet p = new Packet(id,stime,size);
			plist.add(p);
			nop++;	
		}
		
		
		return true;
	}
	
	 void sendpacketPs(int timer,Switch sw) {
		// TODO Auto-generated method stub
		// In packet switching any source can send packet to switch at any time
		 int i=0;
		 //delay = 0;
		 // while(i<psr && plist.size()!=0){
			//  Packet p = plist.get(0);
			//  if(p.send <= timer){
			//  	plist.remove(0);
			 
			//  boolean flag = sw.sendtoSwitch(p,timer,bandwidth);
			//  if(flag==false){
			//  	dropedpackets++;
			//  }
			//  i++;
			//  sendpackets++;
			// }else{
			// 	break;
			// }
		 // }
		 double capacity = bandwidth;
		 while(capacity!=0){
			 boolean flag=true;
			 Packet p = plist.get(0);
			 if(p.send<=timer&&p.size<=capacity){
			 	flag = sw.sendtoSwitch(p,timer,bandwidth);
			 	plist.remove(0);
			 	 sendpackets++;
			 	capacity = capacity - p.size;
			 }else if(p.send<=timer){
			 	p.size = p.size - capacity;
			 	capacity = 0;
			 }else{
			 	break;
			 }
			 if(flag==false){
			 	dropedpackets++;
			 }
			 
			
			 
		 }
	}
}
