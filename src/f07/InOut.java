package f07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class InOut {
	
    public static void copy(InputStream is, OutputStream os) throws IOException {
        int data = is.read();
        while(data!=-1) {
            os.write(data);
            data = is.read();
        }
        os.flush();
    }
	
    public static void copy(InputStream is, OutputStream os, int buffer) throws IOException {
		byte[] input = new byte[buffer];
		int b = is.read(input,0,input.length);
		while(b!=-1) {
			os.write(input, 0, b);
			b = is.read(input,0,input.length);
		}
		os.flush();
    }
    
    public static void copy(Reader in, Writer out, int buffer) throws IOException {
		char[] input = new char[buffer];
		int n = in.read(input,0,input.length);
		while(n!=-1) {
			out.write(input, 0, n);
			n = in.read(input,0,input.length);
		}
		out.flush();
    }
    
    public static void copy(BufferedReader in, BufferedWriter out) throws IOException {
		String txt = in.readLine();
		while(txt!=null) {
			out.write(txt);
			out.newLine();
			txt = in.readLine();
		}
		out.flush();
    }
}
