package f03;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class DemoReaderWriter2 implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	String filename = fileChooser.getSelectedFile().getPath();
        	BufferedWriter bw = null;
        	try {       		
        		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"ISO-8859-1"));
//        		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),"UTF-8"));
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
        	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"ISO-8859-1"))) {
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"))) {
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
                new Controller(new DemoReaderWriter2(),"ReaderWriter2");
            }
        });
	}
}
