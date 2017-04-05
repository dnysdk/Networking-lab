import java.util.*;

public class Source {
	double bandwidth;
	double filesize;
	double size;
	int nop;
	double delay = 0;
	double windowsize;
	int rtt = 4;
	double pd ;
	double t = 0;
	Router r = new Router();
	ArrayList<Packet> packets = new ArrayList<Packet>();
	ArrayList<Packet> packetlist;
	Source(double bandwidth,double filesize,double size,double windowsize,double pd){
		this.bandwidth = bandwidth;
		this.filesize = filesize;
		this.size = size;
		this.windowsize = windowsize;
		nop = (int)(filesize/size);
		if((filesize/size)!=0)
			nop = nop + 1;
		for(int i=0;i<nop;i++){
			Packet p = new Packet(0,size);
			packets.add(p);
		}
		this.pd = pd;
		packetlist = new ArrayList<Packet>();
		//System.out.println(windowsize);
		for(int i=0;i<windowsize;i++){
			packetlist.add(packets.get(0));
			packets.remove(0);
		}
	}
	
	void sendfile(){
		int time = 1;
		while(time>0){
			if(packets.size()==0){
				break;
			}
			//System.out.println("loop");
			int index = 0;
			for(int i=0;i<packetlist.size();i++){
				Packet p = packetlist.get(i);
				//if(packetlist.size()!=windowsize){
					
					//System.out.println("size "+packetlist.size());
					Random random = new Random();
					double randomNumber =(random.nextInt(10) + 1)/10.0;
					//System.out.println(randomNumber);
					if(pd>randomNumber && time>=p.timeout){
						p.send_time = time;
						p.tosink = true;
						//System.out.println("sending");
						Packet g = r.sendToRouter(p,r,time);
						//if(g!=null){
							g.end_time = time + 1;
							delay = delay + g.end_time - g.send_time;
							packetlist.remove(i);
							//System.out.println("removed");
							i--;
							//System.out.println("abc");
						//}
					}else if(pd<=randomNumber && time>=p.timeout){
						p.timeout = p.timeout + windowsize*time;
						//System.out.println();
						//index++;
						//System.out.println("no send");
					}
				//}
			}
			while(packetlist.size()<windowsize){
				//System.out.println("added");
				// System.out.println(packetlist.size());
				// System.out.println(windowsize);
				if(packets.size()>0){
				packetlist.add(packets.get(0));
				packets.remove(0);
			}else{
				break;
			}
			}
			time++;
		}
	}

}
