package p1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectfileProducer implements MessageProducer {

	private int times = 0;
	private int delay = 0;
	private Message[] messages;
	private int currentIndex = -1;

	public ObjectfileProducer(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {

			times    = ois.readInt();
			delay    = ois.readInt();
			int size = ois.readInt();
			
			messages = new Message[size];
			
			for (int i = 0; i < messages.length; i++) {
				messages[i] = (Message) ois.readObject();
			}

			info();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return times;
	}

	@Override
	public int size() {
		if (messages == null) {
			return 0;
		} else {
			return messages.length;
		}
	}

	@Override
	public Message nextMessage() {
		if (size() == 0) {
			return null;
		}
		currentIndex = (currentIndex + 1) % messages.length;
		return messages[currentIndex];
	}

}
