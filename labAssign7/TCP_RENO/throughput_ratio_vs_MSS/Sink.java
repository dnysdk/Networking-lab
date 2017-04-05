
public class Sink {
	
	Packet send(Packet p){
		//System.out.println("in sink;");
		p.isack = true;
		p.tosource = true;
		p.tosink = false;
		return p;
	}
}
