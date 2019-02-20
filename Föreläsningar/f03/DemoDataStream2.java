package f03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class DemoDataStream2 implements StringIO {
    private JFileChooser fileChooser = new JFileChooser();

	@Override
	public void write(String str) {
		try {
			Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, "DA343ACipherTest");
			if (cipher != null && fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				try(
						FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile().getPath());
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						CipherOutputStream cos = new CipherOutputStream(bos, cipher);
						DataOutputStream dos = new DataOutputStream(cos) ) {

	        		dos.writeInt(str.length());
	        		dos.writeBoolean(str.length()>20);
	        		dos.writeUTF(str);
	        		dos.flush();
				} catch(IOException e) {
					System.err.println(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override 
	public String read() {
		String str = null;
		Cipher cipher = getCipher(Cipher.DECRYPT_MODE, "DA343ACipherTest");
		if (cipher != null && fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	try (CipherInputStream cis = new CipherInputStream(
        			new BufferedInputStream(
        					new FileInputStream(fileChooser.getSelectedFile().getPath())), cipher);
        			DataInputStream dis = new DataInputStream(cis) ) {
        		
        		int len = dis.readInt();
        		boolean longStr = dis.readBoolean();
        		String txt = dis.readUTF();
        		
        		if(longStr) {
        			str = "En lång sträng, ";
        		} else {
        			str = "En kort sträng, ";
        		}
        		str = str + len + " characters in the String-objekt\n";
        		str = str + txt;        		
        	} catch(IOException e) {
        		System.err.println(e);
        	} 
        }
		return str;
	}

	private Cipher getCipher(int opmode, String secretKey) {
		Cipher cipher = null;
		try {
			Key key = new SecretKeySpec(secretKey.getBytes("UTF-8"),"AES"); // UnsupportedEncodingException
			cipher = Cipher.getInstance("AES"); // NoSuchAlgorithmException, NoSuchPaddingException
			cipher.init(opmode, key); // InvalidKeyException
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cipher;
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller(new DemoDataStream2(),"DemoDataStreams2");
            }
        });
	}
}
