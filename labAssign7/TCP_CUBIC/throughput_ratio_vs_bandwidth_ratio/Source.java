import java.util.*;

public class Source {
	int bandwidth;
	double filesize;
	double size;
	int nop;
	int windowmax1=0;
	int windowmax2=0;
	double delay1 = 0;
	double delay2 = 0;
	double c=0.2;
	double beta=0.4;
	double lasttime1=0;
	double lasttime2=0;
	int windowsize1;
	int windowsize2;
	int time = 1;
	int i=0;
	double t = 0;
	int endtime1 = 0;
	int endtime2 = 0;
	Router r;
	int countSend1;
	int countSend2;
	ArrayList<Packet> packetList1 = new ArrayList<Packet>();
	ArrayList<Packet> packetList2 = new ArrayList<Packet>();
	int runtime;
	int r1,r2;
	// ArrayList<Packet> global = new ArrayList<Packet>();
	// ArrayList<Packet> packetlist;
	Source(int bandwidth,double filesize,double size,int windowsize,int r1,int r2){
		this.r1 = r1;
		this.r2 = r2;
		this.bandwidth = bandwidth;
		this.filesize = filesize;
		this.size = size;
		this.windowsize1 = 1;
		this.windowsize2 = 1;
		// this.runtime = t;
		nop = (int)(filesize/size);
		if((filesize/size)!=0)
			nop = nop + 1;
		int counter = 1;
		for(int a=0;a<nop;a++){
			Packet p1 = new Packet(size,1,counter);
			counter++;
			packetList1.add(p1);
			Packet p2 = new Packet(size,2,counter);
			counter++;
			packetList2.add(p2);
		}
		i=0;
		this.countSend1 = 0;
		this.countSend2 = 0;
		r = new Router(r1,r2);
	}
	
	void sendfile(Source s){
		int b = 0;
		int flag = 0;
		while(time>0){

			if(packetList1.size()==0 && endtime1==0){
				endtime1 = time;
			}
			if(packetList2.size()==0 && endtime2==0){
				endtime2 = time;
			}
			if(packetList1.size()==0 && packetList2.size()==0){
				break;
			}
			double k = Math.cbrt(windowmax1*beta/c);
			windowsize1 = (int)(c*(time-lasttime1-k)*(time-lasttime1-k)*(time-lasttime1-k) + windowmax1);
			k = Math.cbrt((double)windowmax2*beta/c);
			windowsize2 = (int)(c*(time-lasttime2-k)*(time-lasttime2-k)*(time-lasttime2-k) + windowmax2);
			// if(time>runtime){
			// 	break;
			// }
			i=0;
			b = bandwidth/2;
			if(b>windowsize1){
				b = windowsize1; 
			}
			// System.out.println(b);
			for(i=0;i<b;i++){
				if(packetList1.isEmpty()|| i>=packetList1.size()){
					break;
				}
				Packet p = packetList1.get(i);
				Packet g = null;	
				if(time>=p.timeout){
					p.send_time = time;
					p.tosink = true;
					//System.out.println("sending");
					g = r.sendToRouter(p,r,time);
				}
				if(time>=p.timeout && g.isack==false){
					p.timeout = p.timeout + 5;
					windowmax1 = windowsize1;
					lasttime1 = time;
					// System.out.println("decrease");
					flag = 1;
					if(windowsize1<=0){
						windowsize1=1;
					}
					break;
				}else{
					flag = 0;
				}
				// if(g.isack==true){
				// 	packetList1.remove(i);
				// 	i--;
				// }
			}
			if(flag==0){
				i=0;
				b = bandwidth/2;
				if(b>windowsize2){
					b = windowsize2; 
				}
				for(i=0;i<b;i++){
					if(packetList2.isEmpty()|| i>=packetList2.size()){
						break;
					}
					Packet p = packetList2.get(i);
					Packet g = null;	
					if(time>=p.timeout){
						p.send_time = time;
						p.tosink = true;
						//System.out.println("sending");
						g = r.sendToRouter(p,r,time);
					}
					if(time>=p.timeout && g.isack==false){
						p.timeout = p.timeout + 5;
						windowmax2 = windowsize2;
						lasttime2 = time;
						// System.out.println("decrease");
						if(windowsize2<=0){
							windowsize2=1;
						}
						break;
					}
					// if(g.isack==true){
					// 	packetList2.remove(i);
					// 	i--;
					// }
				}
			}	
			r.sending(s);
			// System.out.println("windowsize : " + windowsize);
			time++;
		}
			
	}
	

}
