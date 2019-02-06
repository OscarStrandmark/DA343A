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
	// Inre klass vilken vilken h�mtar Game-objekt ur buffer och visar objekten
	// i TextWindow. Anv�nd println-metoden i TextWindow.
}
