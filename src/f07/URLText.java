package f07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class URLText {
	private static void saveToFile(URL source, String filename, boolean append) {
		try( BufferedInputStream bis = new BufferedInputStream(source.openStream());
			 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename, append))) {						
			InOut.copy(bis,bos);
//			InOut.copy(bis,bos,1000);
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	private static void showInConsole(URL source) {
		try( InputStreamReader isr = new InputStreamReader(source.openStream(),"UTF-8");
			 BufferedReader br = new BufferedReader(isr);
			 OutputStreamWriter osw = new OutputStreamWriter(System.out);
			 BufferedWriter bw = new BufferedWriter(osw)) {				
			System.out.println("Text från " + source.toString());

			System.out.println("--- Läsning tecken för tecken ---");
			InOut.copy(br,bw,1000);
				
//			System.out.println("------ Läsning rad för rad ------");
//			InOut.copy(br,bw);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	public static void main(String[] args) throws MalformedURLException {
//			URL u = new URL("http://ddwap.mah.se/tsroax/expendituresxml.xml");
			URL u = new URL("http://ddwap.mah.se/tsroax/expendituresjson.php?type=utgifter");
			showInConsole(u);
//			saveToFile(u,"files/"+u.getFile());
	}
}
