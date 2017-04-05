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
		System.out.println("how much time u want to run simulation ");
		int time = in.nextInt();
		int timedivision = 10;
		System.out.println("enter packet size of each packet");
		int packetsize = in.nextInt();
		System.out.println("enter bandwidth for link ");
		int bandwidth = in.nextInt();//constant for all source
		System.out.println("Enter size of queue in switch");
		int queuesize = in.nextInt();
		for(int lambda=1;lambda<=50;lambda++){
			Source [] s = new Source[nos];
			for(int i=0;i<nos;i++){
				int id = i+1;
				s[i] = new Source(id,bandwidth);
			}
			
			
			int timer,count,num;
			
				timer = 0;
				Switch sw = new Switch(queuesize);
				count=0;
				while(timer<time){
					//generating packets for each source
					for(Source s1 : s){
						s1.generatePacket(timer,packetsize,lambda/5.0);
					}
					
					for(Source s1 : s){
						s1.sendpacketPs(timer,sw);
					}
					Packet p = sw.sendtoSink(timer,bandwidth);
					if(p==null){
						timer++;
						continue;
					}
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
			System.out.println("packet dropped for lambda "+lambda/5.0+" is = "+dropedpackets);
			System.out.println("link utilization in % for lambda "+lambda/5.0+" is = "+linkutilization);
			System.out.println();
		}
				
	}

}
