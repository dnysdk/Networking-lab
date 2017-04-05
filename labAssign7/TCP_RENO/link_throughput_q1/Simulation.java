import java.util.*;
public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		// System.out.println("enter bandwidth for host 1");
		// double bandwidth1 = in.nextDouble();
		// System.out.println("enter bandwidth for host 2");
		// double bandwidth2 = in.nextDouble();
		// System.out.println("enter window size at source");
		// double windowsize = in.nextDouble();
		// System.out.println("enter size of file");
		// double filesize = in.nextDouble();
		double filesize = 10000;
		// size of packet is constant
		double size = 10;
		for(int i = 1;i<2;i++){
			Source s = new Source(400,filesize,size,1);
			s.sendfile(s);
			// double averageDelay1 = (double)s.delay1/(double)2*s.nop;
			// double averageDelay2 = (double)s.delay2/(double)2*s.nop;
			// System.out.println(s.countSend1);
			// double lth = ((double)s.countSend1/((double)i*400))*100;
			// System.out.println(lth);  
			// System.out.println("Actual link throughput for host 1 till time "+ i+" :- " + lth);
			i = i+9;
			// System.out.println("Average Delay for sending file if window size is "+ i+" :- " + averageDelay2);
			// System.out.println();
		}
	}

}
