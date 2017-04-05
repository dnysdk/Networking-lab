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
		System.out.println("how much time u want to run simulation");
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
		System.out.println("Enter size of queue in switch");
		int queuesize = in.nextInt();
		for(int psr=1;psr<=finalpsr;psr++){
			Source [] s = new Source[nos];
			for(int i=0;i<nos;i++){
				int id = i+1;
				s[i] = new Source(id,psr,grate,bandwidth);
			}
			
			
			//starting simulation one iteration is 1 unit of time
			int timer,count,num;
			// double totaldelay,averagedelay;
			
				timer = 0;
				Switch sw = new Switch(queuesize);
				count=0;
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
					//totaldelay += p.end_time - p.start_time;
					//count++;
					timer++;
				}
				int dropedpackets = 0;
				int totalpackets = 0;
				for(Source s1 : s){
					dropedpackets += s1.dropedpackets;
					totalpackets += s1.sendpackets;
				}

				double lossrate = (double)dropedpackets/(double)totalpackets;
				System.out.println("packet loss rate for sending rate "+psr+" is = "+lossrate);
			
			
		}
				
	}

}
