package l06.l6games;

import java.io.IOException;

public class Main {

	private static String filepath = "src\\l06\\games.txt";

	public static void main(String[] args) throws IOException {
		Buffer<Game> buffer = new Buffer<Game>();
		Controller controller = new Controller(buffer);
		GameResults gr = new GameResults(filepath, buffer);
		gr.startSimulation();
		try {
			Thread.sleep(20500);
		} catch (InterruptedException e) {
		}
		gr.stopSimulation();
	}
}
