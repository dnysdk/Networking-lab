public class Packet {
	int sourceid;
	double start_time;
	double size;
	double end_time;
	double send=0;
	public Packet(int id,double t,double size){
		start_time = t;
		this.size=size;
		end_time = 0;
		send=t;
	}
	
	
	
}
