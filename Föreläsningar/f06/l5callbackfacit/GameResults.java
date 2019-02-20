package f06.l5callbackfacit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameResults {
	private ArrayList<Game> games = new ArrayList<Game>();
	private ArrayList<ResultListener> listeners = new ArrayList<ResultListener>();
	private SimulateGames thread;

	public GameResults(String filename) throws IOException {
		try (BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8")) ) {
			String line = bw.readLine();
			String[] teams;
			while(line!=null) {
				teams = line.split(",");
				games.add(new Game(teams[0],teams[1]));
				line = bw.readLine();
			}
		} 
	}
	
	public void addResultListener(ResultListener listener) {
		if(listener!=null) {
			listeners.add(listener);
		}
	}

	public void startSimulation() {
		if(thread==null) {
			thread = new SimulateGames();
			thread.start();
		}
	}

	public void stopSimulation() {
		if(thread!=null) {
			thread.interrupt();
			thread = null;
		}
	}

	private class SimulateGames extends Thread {
		public void run() {
			int gameIndex, team;
			Random rand = new Random();
			Game game;
			while(thread!=null) {
				try {
					Thread.sleep(1000);
					team = rand.nextInt(2);
					gameIndex = rand.nextInt(games.size());
					game = games.get(gameIndex);
					switch(team) {
					    case 0: game.increaseGoal1(); break;
					    case 1: game.increaseGoal2(); break;
					}
					for(ResultListener listener : listeners) {
						listener.newResult(game);
					}
				} catch(InterruptedException e) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		GameResults gr = new GameResults("files/games.txt");
		gr.startSimulation();
		try {
			Thread.sleep(20000);
		} catch(InterruptedException e) {}
		gr.stopSimulation();
	}
}

