package p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;

/**
 * En klass som hämtar en textfil och läser dess innehåll för att skapa {@link Message} objekt.
 * 
 * 
 * @author Oscar Strandmark
 */
public class TextfileProducer implements MessageProducer {

	private int times = 0;
	private int delay = 0;

	private Message[] messages;
	private int currentIndex = -1;

	/**
	 * Skapar ett objekt som läser av .txt filen vid <code>filename</code> och skapar {@link Message} objekt av det som läses från textfilen.
	 * Formatet på filen lyder:<p>
	 * 
	 * a <br>
	 * b <br>
	 * c <br>
	 * m1_text <br>
	 * m1_img_filePath <br>
	 * m2_text <br>
	 * m2_img_filePath <br>
	 * osv...<p>
	 * 
	 * a = Antalet gånger sekvensen ska upprepas<br>
	 * b = Antal millisekunder var bild ska visas<br>
	 * c = Antal meddelanden i sekvensen<p>
	 * @param filename Sökväg till filen som skall användas
	 */
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

	/**
	 * Returnerar antalet millisekunder (ms) mellan var {@link Message}-objekt visas.
	 * 
	 * @return Returnerar antalet millisekunder (ms) mellan var {@link Message}-objekt visas.
	 */
	@Override
	public int delay() {
		return delay;
	}

	/**
	 * Returnerar antal gånger {@link Message}-sekvensen skall visas.
	 * 
	 * @return Returnerar antal gånger {@link Message}-sekvensen skall visas.
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 * Returnerar storleken på {@link Message}-sekvensen.
	 * 
	 * @return Returnerar storleken på {@link Message}-sekvensen.
	 */
	@Override
	public int size() {
		if(messages == null) {
			return 0;
		} else {
			return messages.length;
		}
	}

	/**
	 * Returnerar nästa {@link Message}-objekt i sekvensen.
	 * 
	 * @return Returnerar nästa {@link Message}-objekt i sekvensen.
	 */
	@Override
	public Message nextMessage() {
		if (size() == 0) {
			return null;
		}
		currentIndex = (currentIndex+1) % messages.length;
		return messages[currentIndex];
	}

}
