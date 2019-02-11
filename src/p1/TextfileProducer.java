package p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

public class TextfileProducer implements MessageProducer {

	private int times = 0;
	private int delay = 0;

	private Message[] messages;
	private int currentIndex = -1;

	public TextfileProducer(String filename)  {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			times = Integer.parseInt(br.readLine());
			delay = Integer.parseInt(br.readLine());
			int size  = Integer.parseInt(br.readLine());
			
			messages = new Message[size];
			
			for (int i = 0; i < messages.length; i++) {
				String text = br.readLine();
				ImageIcon icon = new ImageIcon(br.readLine());
				messages[i] = new Message(text,icon);
			}
			br.close();

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
		if(messages == null) {
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
		currentIndex = (currentIndex+1) % messages.length;
		return messages[currentIndex];
	}

}
