public class Packet {
	// String sourceip;
	// String sourceport;
	// String destinationip;
	// String destinationport;
	double send_time;
	int packetid;
	double timeout;
	double size;
	boolean isack;
	double end_time;
	int hostid;
	boolean tosource;
	boolean tosink;
	public Packet(double size,int id,int pid){
		send_time = 0;
		this.size=size;
		timeout = 0;
		end_time = 0;
		isack = false;
		tosource = false;
		tosink = false;
		this.hostid = id;
		this.packetid = pid;
	}	
}