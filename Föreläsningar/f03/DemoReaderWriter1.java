package f03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class DemoReaderWriter1 implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	String filename = fileChooser.getSelectedFile().getPath();
        	BufferedWriter bw = null;
        	try {       		
        		bw = new BufferedWriter(new FileWriter(filename));
        		bw.write(str);
        		bw.flush();
        	} catch(IOException e) {
        		System.err.println(e);
        	} finally {
        		if(bw != null) {
        			try {
        				bw.close();
        			} catch(IOException e){}
        		}
        	}
        }
	}

	@Override
	public String read() {
		StringBuffer str = new StringBuffer();
        int chr;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	String filename = fileChooser.getSelectedFile().getPath();
        	try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        		chr = br.read();
        		while (chr != -1) {
        			str.append((char)chr);
        			chr = br.read();
        		}
        	} catch(IOException e) {
        		System.err.println(e);
        	} 
        }
		return str.toString();
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new DemoReaderWriter1(),"ReaderWriter1");
            }
        });
	}
}
