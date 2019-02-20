package l06.laboration6;

import java.io.File;

public class Main_U4b {
	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer();
		File file = new File("files/Material 6");
		ZipArchive archive = new ZipArchive(file, buffer);
		StringConsumer cons = new StringConsumer(buffer);
		//archive.zip();
		cons.start();
	}
}
