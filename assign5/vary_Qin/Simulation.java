import java.util.*;
public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("enter bandwidth of link");
		double bandwidth = in.nextDouble();
		System.out.println("enter window size at source");
		double windowsize = in.nextDouble();
		System.out.println("Enter probability of packet droping");
		double probability = in.nextDouble();  // probability of packet dropping
		System.out.println("enter size of file");
		double filesize = in.nextDouble();
		// size of packet is constant
		double size = 10;
		for(int i = 1;i<windowsize;i++){
			
			Source s = new Source(bandwidth,filesize,size,i,probability);
			s.sendfile();
			double averageDelay = (double)s.delay/(double)2*s.nop;
			
			System.out.println("Average Delay for sending file if window size is "+ i+" :- " + averageDelay);
		}
	}

}
