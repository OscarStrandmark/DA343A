package l06.l6games;

public class Controller {
	private Buffer<Game> buffer;
	
	public Controller(Buffer<Game> buffer) {
		this.buffer = buffer;
		new Results().start();
	}
	
	private class Results extends Thread {
		public void run() {
			Game game;
			while(!Thread.interrupted()) {
				try {
					game = buffer.get();
					TextWindow.println(game);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	// Inre klass vilken vilken hämtar Game-objekt ur buffer och visar objekten
	// i TextWindow. Använd println-metoden i TextWindow.
}
