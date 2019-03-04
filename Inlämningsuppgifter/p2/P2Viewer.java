package p2;

import p1.Message;
import p1.Viewer;

public class P2Viewer extends Viewer{

	private MessageClient mc;
	
	public P2Viewer(MessageClient messageClient, int xSize, int ySize) {
		super(xSize,ySize);
		this.mc = messageClient;
		mc.addListener(new CallbackController());
	}
	
	public void setMessage(Message msg) {
		super.setMessage(msg);
	}

	private class CallbackController implements CallbackInterface {
		public void getMessage(Message msg) {
			setMessage(msg);
		}
	}
}
