package l08.Uppgift_2;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class QuizController {

	private QuizUI ui = new QuizUI(this);
	private QuizClient client;
	
	public QuizController() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(ui);
				frame.pack();
				frame.setVisible(true);
			}
		});
		client = new QuizClient(this);
	}
	
	//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Metoder som QuizClient anropar vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
	public void newQuestion(String txt, int nbr) {
		ui.setText("Fråga: " + nbr + "\n" + txt);
		ui.enableAnswer();
	}
	
	public void newResponse(String txt, int nbr) {
		ui.appendText("\n" + "Resultat fråga " + nbr + ": " + txt);
		ui.enableQuestion();
	}

	public void connected(String txt) {
		ui.appendText("\n" + txt);
		ui.enableQuestion();
		ui.connected();
	}
	
	public void disconnected(String txt) {
		ui.setText(txt);
		disconnect();
	}
	
	//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv Metoder som QuizUI anropar vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
	public void question(String nbrStr) {
		int nbr;
		try {
			nbr = Integer.parseInt(nbrStr);
			client.question(nbr);
			ui.waitingForResponse();
		} catch (NumberFormatException nfe) {
			ui.setText("Inmatning ska vara heltal (fråga nr): " + nbrStr);
		} catch (IOException ioe) {
			ui.setText(ioe.toString());
			disconnect();
		}
	}
	
	public void answer(String nbrStr, String answerStr) {
		try {
			int nbr = Integer.parseInt(nbrStr);
			int answer = Integer.parseInt(answerStr);
			client.answer(nbr,answer);
			ui.waitingForResponse();
		} catch (NumberFormatException nfe) {
			ui.appendText("\nInmatning ska vara heltal, Fråga nr: " + nbrStr + ",Årtal: " + answerStr);
		} catch (IOException ioe) {
			ui.setText(ioe.toString());
			disconnect();
		}
	}
	
	public void connect(String ip, String port) {
		try {
			client.connect(ip,Integer.parseInt(port));
		} catch (Exception e) {
			ui.setText(e.toString());
		}
	}
	
	public void disconnect() {
		try {
			ui.disconnected();
			client.disconnect();
		} catch (IOException ioe) {}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				QuizController prog = new QuizController();
			}
		});
	}
}
