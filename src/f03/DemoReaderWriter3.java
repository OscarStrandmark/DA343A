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

public class DemoReaderWriter3 implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
		String[] rows;
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
        			new FileOutputStream(fileChooser.getSelectedFile().getPath()), "UTF-8")) ) {
        		rows = str.split("\n");
        		for(String s : rows) {
        			bw.write(s);
        			bw.newLine();
        			bw.flush();
        		}
        	} catch(IOException e) {
        		System.err.println(e);
        	}
        }
	}

	@Override
	public String read() {
		StringBuffer res = new StringBuffer();
        String str;
        int line = 0;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	String filename = fileChooser.getSelectedFile().getPath();
        	try (BufferedReader br = new BufferedReader(new InputStreamReader(
        			new FileInputStream(filename),"UTF-8"))) {
        		str = br.readLine();
        		while ( str!=null ) {
        			res.append(++line+". "+str+"\n");
        			str = br.readLine();
        		}
        	} catch(IOException e) {
        		System.err.println(e);
        	} 
        }
		return res.toString();
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new DemoReaderWriter3(),"ReaderWriter3");
            }
        });
	}
}
