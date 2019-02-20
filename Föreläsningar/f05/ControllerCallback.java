package f05;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ControllerCallback implements ZipController {
    private ZipUI gui = new ZipUI(this);	
    
    public ControllerCallback() {
    	JFrame frame = new JFrame("Callback");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.add(gui);
    	frame.pack();
    	frame.setVisible(true);
    }
	
	public void zip(File file) {
		gui.clear();
		ZipArchiveCallback archive = new ZipArchiveCallback(file);
		archive.addProgressListener(new CallbackImpl());
//		zar.addProgressListener(new ProgressListenerConsole());
		archive.zip();
	}

	public void unzip(File file) {
		gui.clear();
		ZipArchiveCallback archive = new ZipArchiveCallback(file);
		archive.addProgressListener(new CallbackImpl());
		archive.unzip();
	}

	private class CallbackImpl implements ProgressListener {
		@Override
		public void progress(String message) {
			gui.append(message);
		}

		@Override
		public void ready(String message) {
			gui.append("ZIP-FILE: " + message);
		}

		@Override
		public void exception(String message) {
			gui.append("EXEPTION: " + message);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ControllerCallback();
			}
		});		
	}
}
