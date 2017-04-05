public class Packet {
	int sourceid;
	int start_time;
	double size;
	double end_time;
	double send;
	public Packet(int id,int t,double size,int bandwidth){
		start_time = t;
		this.size=size;
		end_time = 0;
		send=size/bandwidth;
	}
	
	
	
}
