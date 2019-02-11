package p1;


import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")

public class P1Viewer extends Viewer{

	
	public P1Viewer(MessageManager messageManager, int xSize, int ySize) {
		super(xSize,ySize);
		messageManager.addObserver(new MessageObserver());
	}

	private void sendMsg(Message msg) {
		super.setMessage(msg);
	}
	
	private class MessageObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			o.toString();
			System.out.println("update");
			Message msg = (Message)arg;
			sendMsg(msg);
		}
		
	}
}
