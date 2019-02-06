package l06.l6games;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameResults {
	private ArrayList<Game> games = new ArrayList<Game>();
	private Buffer<Game> buffer;
	private SimulateGames thread;

	public GameResults(String filename) throws IOException {
		try (BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String line = bw.readLine();
			String[] teams;
			while (line != null) {
				teams = line.split(",");
				games.add(new Game(teams[0], teams[1]));
				line = bw.readLine();
			}
		}
	}

	public GameResults(String filename, Buffer<Game> buffer) throws IOException {
		try (BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String line = bw.readLine();
			this.buffer = buffer;
			String[] teams;
			while (line != null) {
				teams = line.split(",");
				games.add(new Game(teams[0], teams[1]));
				line = bw.readLine();
			}
		}
	}

	public void startSimulation() {
		if (thread == null) {
			thread = new SimulateGames();
			thread.start();
		}
	}

	public void stopSimulation() {
		if (thread != null) {
			thread.interrupt();
			thread = null;
		}
	}

	private class SimulateGames extends Thread {
		public void run() {
			int gameIndex, team;
			Random rand = new Random();
			Game game;
			while (thread != null) {
				try {
					Thread.sleep(1000);
					team = rand.nextInt(2);
					gameIndex = rand.nextInt(games.size());
					game = games.get(gameIndex);
					switch (team) {
					case 0:
						game.increaseGoal1();
						break;
					case 1:
						game.increaseGoal2();
						break;
					}
					buffer.put(game);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}
