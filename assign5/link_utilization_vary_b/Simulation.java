import java.util.*;
public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("enter window size at source");
		double windowsize = in.nextDouble();
		System.out.println("Enter probability of packet droping");
		double probability = in.nextDouble();  // probability of packet dropping
		System.out.println("enter size of file");
		double filesize = in.nextDouble();
		// size of packet is constant
		double size = 10;
		for(int i = 10;i<=70;){
			
			Source s = new Source(i,filesize,size,windowsize,probability);
			s.sendfile();
			int totalSend = s.nop;
			int maxSend = i*(s.time);
			double linkutil = (double)totalSend/(double)maxSend;
			
			//double averageDelay = (double)s.delay/(double)2*s.nop;
			
			System.out.println("Link Utilization for sending file if bandwidth is "+ i+" :- " + linkutil);
			i = i+10;
		}
	}

}
