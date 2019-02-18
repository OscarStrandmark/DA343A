package f07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLConnectionEx {
	public static void main(String[] args) {
		try {
			URL u = new URL("http://ddwap.mah.se/tsroax/expendituresxml.xml");
			URLConnection uc = u.openConnection();
			System.out.println("Content-type: " + uc.getContentType());
			if (uc.getContentEncoding() != null) {
				System.out.println("Content-encoding: "
						+ uc.getContentEncoding());
			}
			if (uc.getDate() != 0) {
				System.out.println("Date: " + new Date(uc.getDate()));
			}
			if (uc.getLastModified() != 0) {
				System.out.println("Last modified: "
						+ new Date(uc.getLastModified()));
			}
			if (uc.getExpiration() != 0) {
				System.out.println("Expiration date: "
						+ new Date(uc.getExpiration()));
			}
			if (uc.getContentLength() != -1) {
				System.out.println("Content-length: " + uc.getContentLength());
			}
			
			BufferedReader isr = new BufferedReader(new InputStreamReader(u.openStream(),"UTF-8"));
			int c;
			while((c=isr.read())!=-1) {
				System.out.print((char)c);
			}
			isr.close();
		} catch (MalformedURLException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		} 
	}
}
