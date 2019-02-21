package l08.Uppgift_3;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CalcClientD implements CalcClient {

	private CalcController controller;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public CalcClientD(String address, int port) throws IOException {
		socket = new Socket(address, port);
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		new Listener().start();
	}

	@Override
	public void newCalculation(String expression) throws IOException {

		String[] parts = expression.split(",");
		
		Double nbr1 = Double.parseDouble(parts[0]);
		Double nbr2 = Double.parseDouble(parts[1]);
		char operation = parts[2].charAt(0);
		Expression exp = new Expression(nbr1, nbr2, operation);

		oos.writeObject(exp);
	}

	@Override
	public void setCalcController(CalcController controller) {
		this.controller = controller;
	}

	private class Listener extends Thread {
		public void run() {
			Calculation response;
			try {
				while (true) {
					response = (Calculation)ois.readObject();
					controller.newResponse(response.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				socket.close();
			} catch (Exception e) {
				controller.newResponse("Klienten kopplar ner");
			}
		}
	}
}
