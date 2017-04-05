import java.util.ArrayList;

public class Source {
	int id;
	int psr;//sending rate
	int generating_rate;
	int nop;//number of available packets
	double delay;
	int sendpackets;
	int bandwidth;
	ArrayList<Packet> plist = new ArrayList<Packet>();
	Source(int id,int psr,int generating_rate,int bandwidth){
		this.id = id;
		this.psr=psr;
		this.generating_rate=generating_rate;
		this.nop=0;
		this.delay=0;
		sendpackets = 0;
		this.bandwidth=bandwidth;
	}
	boolean generatePacket(int time,int size){
		nop = nop + generating_rate;
		int i=0;
		while(i<generating_rate){
			Packet p = new Packet(id,time,size);
			plist.add(p);
			i++;
			//System.out.println("generated	" + time);
		}
		return true;
	}
	void sendpacketCs(int time) {
		// TODO Auto-generated method stub
		int i=0;
		while(i<psr && plist.size()!=0){
			
			Packet p = plist.remove(0);
			p.end_time = (double)time + p.size/bandwidth ;
			delay = delay + p.end_time-(double)p.start_time;
			i++;
			sendpackets++;
		}
		nop -= i;
	}
	 void sendpacketPs(int timer,Switch sw) {
		// TODO Auto-generated method stub
		// In packet switching any source can send packet to switch at any time
		 int i=0;
		 //delay = 0;
		 while(i<psr && plist.size()!=0){
			 Packet p = plist.remove(0);
			 sw.sendtoSwitch(p,timer,bandwidth);
			 delay = delay + p.end_time-(double)p.start_time;
			 i++;
			 sendpackets++;
		 }
	}
}
