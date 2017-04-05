public class Packet {
	int sourceid;
	int start_time;
	int size;
	double end_time;
	public Packet(int id,int t,int size){
		start_time = t;
		this.size=size;
		end_time = 0;
	}
	
	
	
}
