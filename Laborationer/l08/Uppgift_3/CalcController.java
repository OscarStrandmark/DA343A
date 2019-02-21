package l08.Uppgift_3;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CalcController {

	CalcClient client;
	CalcUI ui = new CalcUI(this);
	
	public CalcController(CalcClient client) {
		this.client = client;
		client.setCalcController(this);
		showCalcUI();
	}
	
	public void newCalculation(String nbr1, String nbr2, String operation) {
		try {
			client.newCalculation(nbr1 + "," + nbr2 + "," + operation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newResponse(final String response) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ui.setResult(response);
			}
		});
	}
	
	private void showCalcUI() {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			JFrame frame = new JFrame("Client");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(ui);
			frame.pack();
			frame.setVisible(true);
		}
	});
	}
	
	public static void main(String[] args) {
		try {
//			CalcClient clientA = new CalcClientA("195.178.227.53",721);
//			new CalcController(clientA);
//			CalcClient clientB = new CalcClientB("195.178.227.53",722);
//			new CalcController(clientB);
//			CalcClient clientC = new CalcClientC("195.178.227.53",723);
//			new CalcController(clientC);
			CalcClient clientD = new CalcClientD("195.178.227.53",724);
			new CalcController(clientD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
