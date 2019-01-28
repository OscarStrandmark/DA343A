package f03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class DemoObjectStreams implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
		String part1 = str.substring(0, str.length()/2);
		String part2 = str.substring(str.length()/2);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try(FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile().getPath());
        			BufferedOutputStream bos = new BufferedOutputStream(fos);
        			ObjectOutputStream oos = new ObjectOutputStream(bos) ) {
        		oos.writeInt(2); // Går bra, DataOutput implementeras
        		oos.writeObject(part1);
        		oos.writeObject(part2);
        		oos.flush();
        	} catch(IOException e) {
        		System.err.println(e);
        	}
        }
	}

	@Override
	public String read() {
		String str = null;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try (ObjectInputStream ois = new ObjectInputStream( new BufferedInputStream(
        			new FileInputStream(fileChooser.getSelectedFile().getPath())))) {
        		int len = ois.readInt();
        		str = len+"\n"+(String)ois.readObject()+"\n"+(String)ois.readObject();
        	} catch(IOException e) {
        		System.err.println(e);
        	} catch(ClassNotFoundException e2) {
        		System.err.println(e2);
        	}
        }
		return str;
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new DemoObjectStreams(),"ObjectStreams");
            }
        });
	}
}
