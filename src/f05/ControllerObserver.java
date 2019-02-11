package f05;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.Observable;
import java.util.Observer;

public class ControllerObserver implements ZipController {
    private ZipUI gui = new ZipUI(this);	
    
    public ControllerObserver() {
    	JFrame frame = new JFrame("Observer");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.add(gui);
    	frame.pack();
    	frame.setVisible(true);
    }
	
	public void zip(File file) {
		gui.clear();
		ZipArchiveObservable zip = new ZipArchiveObservable(file);
		zip.addObserver(new ObserverImpl());
//		zip.addObserver(new ObserverImpl());
//		zip.addObserver(new Console2());
		zip.zip();
	}

	public void unzip(File file) {
		gui.clear();
		ZipArchiveObservable zip = new ZipArchiveObservable(file);
		zip.addObserver(new ObserverImpl());
		zip.unzip();
	}

	private class ObserverImpl implements Observer {
		public void update(Observable source, String s) {
			gui.append(s);
		}

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ControllerObserver();
			}
		});		
	}
}
