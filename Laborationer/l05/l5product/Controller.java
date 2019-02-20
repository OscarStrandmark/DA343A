package l05.l5product;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Controller {
	private ProductUI productUI=new ProductUI(this);
	private ProductGenerator productGenerator = new ProductGenerator(10000);
	
	public Controller() {
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(productUI);
		frame.pack();
		frame.setVisible(true);
		
		productGenerator.addListener(new PListener());
		productGenerator.addListener(new LogToFile("src\\l05\\l5product\\log"));
	}
		
	public void start() {
		int product;
		boolean ok = false;
		try {
			product = Integer.parseInt(productUI.getProduct());
			if(product>=1 && product<=10000) {
				ok = true;
				productGenerator.start(product);
			}
		} catch(NumberFormatException e) {}
		if(!ok) {
			System.out.println("Bad input: " + productUI.getProduct());
		}
	}

	public void stop() {
		productGenerator.stop();
	}
	
	private class LogToFile implements ProductListener {

		private BufferedWriter writer;
		private int rows = 0;
		
		public LogToFile(String filename) {
			try {
				writer = new BufferedWriter(new FileWriter(filename));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void product(String msg) {
			if (rows<10) {
				try {
					rows++;
					writer.write(msg);
					writer.newLine();
					writer.flush();
				} catch(Exception e) {
					e.printStackTrace();
				} 
			} else {
				productGenerator.removeListener(this);
				try {
					if(writer != null) {
						writer.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class PListener implements ProductListener {
		@Override
		public void product(String msg) {
			SwingUtilities.invokeLater(new Runnable() {
				 public void run() {
				 productUI.append(msg);
				 }
			 });
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
