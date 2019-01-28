package l03;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;

public class Uppgift_6 {

	public void copyFile(String source) {

		// Uppgift 6a
		String destination = source + ".copy";
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination))) {

			Uppgift_6.copy(bis, bos);

			bos.flush();
			bos.close();
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Upgift 6b
	public static void copy(InputStream is, OutputStream os) {
		try {
			int data = is.read();

			while (data != -1) {
				os.write(data);
				data = is.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Uppgift 6c
	public void zip(File[] files) {

		String archivePath = files[0].getParent() + "/archive.zip";

		try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archivePath)))) {

			for(File file : files) {
				
				try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
					String filename = file.getName();
					zos.putNextEntry(new ZipEntry(filename));
					Uppgift_6.copy(bis, zos);
					zos.closeEntry();
					zos.flush();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Uppgift 6d
	public void unzip(File file) {
		String directoryName = withoutSuffix(file.getPath());
		File directory = new File(directoryName);
		directory.mkdir();
		ZipEntry zipEntry;
		
		try(ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)))){
			while((zipEntry = zis.getNextEntry()) != null) {
				try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(directory,zipEntry.getName())))){
					Uppgift_6.copy(zis, bos);
				} catch(IOException e) {
					System.err.println(e);
				}
			}	
		} catch(IOException e) {
			System.err.println(e);
		}

	}
	
	private String withoutSuffix(String filename) {
		int dotIndex = filename.indexOf(".");
		if(dotIndex == -1) {
			return filename;
		}
		return filename.substring(0, dotIndex);
	}

	public static void main(String[] args) {
		Uppgift_6 u6 = new Uppgift_6();
		String filepath;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File files = fileChooser.getSelectedFile();
			u6.unzip(files);
		}
	}
}
