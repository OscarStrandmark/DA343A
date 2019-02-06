package l06.laboration6;

public class Main_U4a {

	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer();
		StringProducer prod = new StringProducer(buffer);
		StringConsumer cons = new StringConsumer(buffer);
		
		prod.start();
		cons.start();
	}

}
