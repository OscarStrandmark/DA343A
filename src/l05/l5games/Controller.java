package l05.l5games;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Controller implements ResultController {
	private GamesUI resultUI;
	private GameResults result;
	
	public Controller() {
		try {
		    result = new GameResults("src\\l05\\l5games\\games.txt");
		    resultUI = new GamesUI(this);
			showFrame(resultUI);
			result.addObserver(new GameObserver());
			result.addObserver(new ConsolePrinter());
		} catch(IOException e) {}
	}

	private void showFrame(JPanel panel) {
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void start() {
		if(result!=null) {
		    result.startSimulation();
		}
	}
	
	@Override
	public void stop() {
		if(result!=null) {
			result.stopSimulation();
		}
	}
	
	private class GameObserver implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			Game game = (Game)arg;
			SwingUtilities.invokeLater(new Runnable() {
				 public void run() {
				 resultUI.newResult(game.toString());
				 }
				});
		}
	}
	
	private class ConsolePrinter implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			Game game = (Game)arg;
			System.out.println(game.toString());
		}
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
			}
		});		
	}
}
