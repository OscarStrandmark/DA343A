package p1;

import java.util.Observable;

public class MessageManager extends Observable implements Runnable {

	private Buffer<Message> messageBuffer;
	private Thread thread;

	public MessageManager(Buffer<Message> messageBuffer) {
		this.messageBuffer = messageBuffer;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Message msg = messageBuffer.get();
				System.out.println(msg.getText());
				setChanged();
				notifyObservers(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
}
