import java.util.*;	
public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of Suorces");
		int nos = in.nextInt();
		System.out.println("how much time ");
		int time = in.nextInt();
		int timedivision = 10;
		System.out.println("enter packet size of each packet");
		int packetsize = in.nextInt();
		System.out.println("enter maximum packet sending rate(packets per unit time) for source");
		int finalpsr = in.nextInt();// constant for all sources 
		System.out.println("enter packet generating rate(packets per unit) for source ");
		int grate = in.nextInt();//constant for all source
		System.out.println("enter bandwidth for source ");
		int bandwidth = in.nextInt();//constant for all source
		System.out.println("enter 1 for circuit switching and 2 for packet switching");
		int decider = in.nextInt();
		for(int psr=1;psr<=finalpsr;psr++){
			Source [] s = new Source[nos];
			for(int i=0;i<nos;i++){
				int id = i+1;
				s[i] = new Source(id,psr,grate,bandwidth);
			}
			
			
			//starting simulation one iteration is 1 unit of time
			int timer,count,num;
			double totaldelay,averagedelay;
			switch(decider){
			case 1:
				timer = 0;
				while(timer<time){
					//generating packets for each source
					for(Source s1 : s){
						s1.generatePacket(timer,packetsize);
					}
					int check = timer/timedivision;
					int i = check%nos;		//selecting source according to time time division 
					
					s[i].sendpacketCs(timer);   //sending packets
					timer++;
				}
				totaldelay=0;
				count=0;
				num = 1;
				for(Source s1 : s){
					totaldelay += s1.delay;
					count += s1.sendpackets;
					//System.out.println("delay for Source "+ num+ " = "+s1.delay);
					num++;
				}
				averagedelay = totaldelay/count;
				//System.out.println("Total delay = "+totaldelay);
				//System.out.println("total number of packet send successfully = "+ count);
				System.out.println("average sending delay if packet sending rate  "+psr+" is = "+averagedelay);
				break;

			case 2 :
				timer = 0;
				Switch sw = new Switch();
				count=0;
				totaldelay=0;
				while(timer<time){
					//generating packets for each source
					for(Source s1 : s){
						s1.generatePacket(timer,packetsize);
					}
					
					for(Source s1 : s){
						s1.sendpacketPs(timer,sw);
					}
					Packet p = sw.sendtoSink(timer,bandwidth);
					if(p==null){
						timer++;
						continue;
					}
					totaldelay += p.end_time - p.start_time;
					count++;
					timer++;
				}
				averagedelay = 0;
				averagedelay = totaldelay/count;
				System.out.println("average sending delay with packet sending rate "+psr+" is = "+averagedelay);
				break;
				default :
					System.exit(0);
			}
			
		}
				
	}

}
