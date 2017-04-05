import java.util.ArrayList;

public class Source {
	int id;
	int psr;//sending rate
	int generating_rate;
	int nop;//number of available packets
	double delay;
	int sendpackets;
	int bandwidth;
	int dropedpackets;
	ArrayList<Packet> plist = new ArrayList<Packet>();
	Source(int id,int psr,int generating_rate,int bandwidth){
		this.id = id;
		this.psr=psr;
		this.generating_rate=generating_rate;
		this.nop=0;
		this.delay=0;
		sendpackets = 0;
		dropedpackets = 0;
		this.bandwidth=bandwidth;
	}
	boolean generatePacket(int time,int alpha){
		nop = nop + generating_rate;
		
		//System.out.println(packetsize);
		int i=0;
		while(i<generating_rate){
			double k=1.0;
			double random = Math.random();
			random = 1-random;
			double size = Math.pow(random,1.0/alpha);
			double packetsize = (double)k/size;
			//System.out.println(packetsize);
			Packet p = new Packet(id,time,10*packetsize,bandwidth);
			plist.add(p);
			i++;
		}
		return true;
	}
	
	 void sendpacketPs(int timer,Switch sw) {
		int i=0;
		 //delay = 0;
		// double capacity = bandwidth;
		//  while(capacity!=0){
		// 	 boolean flag=true;
		// 	 Packet p = plist.get(0);
		// 	 if(p.send<=timer&&p.size<=capacity){
		// 	 	flag = sw.sendtoSwitch(p,timer,bandwidth);
		// 	 	plist.remove(0);
		// 	 	 sendpackets++;
		// 	 	capacity = capacity - p.size;
		// 	 }else if(p.send<=timer){
		// 	 	p.size = p.size - capacity;
		// 	 	capacity = 0;
		// 	 }else{
		// 	 	break;
		// 	 }
		// 	 if(flag==false){
		// 	 	dropedpackets++;
		// 	 }
			 
			
			 
		//  }
		while(i<3 && plist.size()!=0){
			 Packet p = plist.remove(0);
			 boolean flag = sw.sendtoSwitch(p,timer,bandwidth);
			 if(flag==false){
			 	dropedpackets++;
			 }
			 i++;
			 sendpackets++;
		 }
	}
}
