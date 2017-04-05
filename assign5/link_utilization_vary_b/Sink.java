
public class Sink {
	
	Packet sendToSink(Packet p,int time,Router r){
		//System.out.println("in sink;");
		p.isack = true;
		p.tosource = true;
		p.tosink = false;
		return p;
	}
}
