package f03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DemoDataStream1 implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try( DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
        			new FileOutputStream(fileChooser.getSelectedFile().getPath()))) ) {
        		dos.writeInt(str.length());
        		dos.writeBoolean(str.length()>20);
        		dos.writeUTF(str);
        		dos.flush();
        	} catch(IOException e) {
        		System.err.println(e);
        	}
        }
	}

	@Override
	public String read() {
		String str = null;
//	    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Data files","dat")); // l�gger till ett filter i filterlistan
//	    fileChooser.setFileFilter(new FileNameExtensionFilter("Data files","dat")); // l�gger till filtret och s�tter det till aktuellt filter
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try (DataInputStream dis = new DataInputStream(
        			new BufferedInputStream(
        				new FileInputStream(fileChooser.getSelectedFile().getPath())))) {
        		int len = dis.readInt();
        		boolean longStr = dis.readBoolean();
        		String txt = dis.readUTF();
        		
        		if(longStr) {
        			str = "En l�ng str�ng, ";
        		} else {
        			str = "En kort str�ng, ";
        		}
        		str = str + len + " tecken i String-objektet\n";
        		str = str + txt;
        		
        	} catch(IOException e) {
        		System.err.println(e);
        	} 
        }
		return str;
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new DemoDataStream1(),"DemoDataStreams1");
            }
        });
	}
}
