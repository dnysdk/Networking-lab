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
		int k=1;
		int grate = 5;//constant for all source
		System.out.println("enter bandwidth for link ");
		int bandwidth = in.nextInt();//constant for all source
		System.out.println("Enter size of queue in switch");
		int queuesize = in.nextInt();
		for(int alpha=1;alpha<=10;alpha++){
			Source [] s = new Source[nos];
			for(int i=0;i<nos;i++){
				int id = i+1;
				s[i] = new Source(id,2,grate,bandwidth);
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
					s1.generatePacket(timer,alpha);
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
			double utilization = sw.utilization;
			double maxlinkutilization = time*bandwidth;
			double linkutilization = (utilization/maxlinkutilization)*100;	
			double lossrate = (double)dropedpackets/(double)totalpackets;
			System.out.println("packets dropped for alpha "+alpha+" is = "+dropedpackets);
			System.out.println("link utilization in % for alpha "+alpha+" is = "+linkutilization);
			System.out.println();
		}
				
	}

}
