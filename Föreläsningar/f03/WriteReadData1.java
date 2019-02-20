package f03;
import java.io.*;

public class WriteReadData1 {
	private String filename = "files/div1.dat";
	
    public void writeNumbers() throws IOException {
    	FileOutputStream fos = null;     // Alt 1
    	BufferedOutputStream bos = null; // Alt 1
    	DataOutputStream dos = null;     // Alt 1 och Alt 2
    	try {
    		fos = new FileOutputStream(filename); // Alt 1
    		bos = new BufferedOutputStream(fos);  // Alt 1
    		dos = new DataOutputStream(bos);      // Alt 1
//    		dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename))); // Alt 2
    		int[] numbers = new int[10000];

    		for (int i = 0; i < numbers.length; i++) {
    			numbers[i] = (int) (Math.random() * 900) + 100;  // jmf: ...=rand.nextInt(900) + 100; // rand instans av Random
    			if (i % 100 == 0) {
        			if (i % 2000 == 0) {
        				System.out.println();
        			}
    				System.out.print(numbers[i] + " ");
    			}
    		}

    		dos.writeInt(numbers.length); // antal int i datafilen
    		for (int i = 0; i < numbers.length; i++) {
    			dos.writeInt(numbers[i]);
    		}
    		dos.writeUTF(numbers.length + " tal i filen " + filename);
    		dos.flush();
    	} catch(IOException e1) {
    		throw e1;
    	} finally {
			if(dos!=null) {
				try {
					dos.close();
				} catch(IOException e) {}
			}
    	}
    }
    
    public void readNumbers() throws IOException {
    	try( DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename))) ) {
    		int count = dis.readInt(); // antal att läsa
    		int[] numbers = new int[count];
    		for (int i = 0; i < numbers.length; i++) {
    			numbers[i] = dis.readInt();
    		}
    		for (int i = 0; i < numbers.length; i++) {
    			if (i % 100 == 0) {
        			if (i % 2000 == 0) {
        				System.out.println();
        			}
    				System.out.print(numbers[i] + " ");
    			}
    		}
    		System.out.println("\n"+dis.readUTF());
    	}
    }

    public static void main(String args[]) throws IOException {
        WriteReadData1 dt = new WriteReadData1();
        dt.writeNumbers();
        System.out.println(
                "\n--------------------------------------------------------");
        dt.readNumbers();
    }
}
