
public class Packet {
	String sourceip;
	String sourceport;
	String destinationip;
	String destinationport;
	double send_time;
	double timeout;
	double size;
	boolean isack;
	double end_time;
	boolean tosource;
	boolean tosink;
	public Packet(int t,double size){
		send_time = t;
		this.size=size;
		timeout = 0;
		end_time = 0;
		isack = false;
		tosource = false;
		tosink = false;
	}	
}