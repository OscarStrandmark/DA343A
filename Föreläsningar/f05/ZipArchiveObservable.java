package f05;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Observable;
import java.util.Observer;

public class ZipArchiveObservable extends Observable {
	private File file;
	private String archive;
	
	public ZipArchiveObservable(File file) {
		this.file = file;
	}
	
	public void zip() {
		new ZipActivity().start();
	}
	
	public void unzip() {
		new UnzipActivity().start();
	}

	private class ZipActivity extends Thread {	
		public void run() {
			notifyObservers("TO ZIP: " + file.getAbsolutePath());
			if(file.isDirectory()) {
				archive = file.getAbsolutePath()+".zip";
			} else if(file.isFile()) {
				archive = getFileName(file.getAbsolutePath())+".zip";
			} else {
				notifyObservers("NOT directory or file: " + file.getAbsolutePath());
				return;
			}

			try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archive)))){
				zip(file, zos, "");
			}catch(Exception e) {
				notifyObservers("EXEPTION: " + e.getMessage());
				return;
			}
			notifyObservers("ZIP-FILE: " + archive);
		}
		
		private String getFileName(String filename) {
			int index = filename.indexOf('.');
			if(index>=0)
				return filename.substring(0,index);
			else
				return filename;
		}
		
		private void zip(File file, ZipOutputStream zos, String directories) throws IOException {
			if(file.isFile()) {
				notifyObservers("ZIP: " + file.getAbsolutePath());
				zos.putNextEntry(new ZipEntry(directories+file.getName()));
				try (FileInputStream fis = new FileInputStream(file)) {
					int b = fis.read();
					while(b!=-1) {
						zos.write(b);
						b = fis.read();
					}
					zos.flush();
				}
				zos.closeEntry();
			} else if(file.isDirectory()) {
				for(File f : file.listFiles()) {
					zip(f,zos,directories+file.getName()+"/");
				}
			}
		}
	}
	
	private class UnzipActivity extends Thread {
		private File directory;
		private HashMap<String,File> directories = new HashMap<String,File>();
		
		public void run() {
			directory = file.getParentFile();
			notifyObservers("TO UNZIP: " + file.getAbsolutePath() + " to " + directory.toString());
			try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)))){
				unzip(file, zis, "");
			}catch(Exception e) {
				notifyObservers("EXEPTION: " + e.getMessage());
				return;
			}
		}
		
		private String getFileName(String filename) {
			int index = filename.indexOf('.');
			if(index>=0)
				return filename.substring(0,index);
			else
				return filename;
		}
		
		private void unzip(File file, ZipInputStream zis, String directories) throws IOException {
			ZipEntry entry;
			while((entry=zis.getNextEntry())!=null) {
				notifyObservers("UNZIP: " + entry.getName() +" to " + directory + "/");
				checkDirectories(entry.getName());
				try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(directory+"/"+entry.getName()))) {
					int b = zis.read();
					while(b!=-1) {
						bos.write(b);
						b = zis.read();
					}
					bos.flush();
				}
			}
		}
		
		
		private void checkDirectories(String file) {
			int index = file.indexOf("/");
			while(index>=0) {
				String path = file.substring(0,index);
				if(directories.get(path)==null) {
					new File(directory+"/"+path).mkdir();
				}
				index = file.indexOf("/",index+1);
			}
		}
	}
}

class Console2 implements Observer {
	public void update(Observable observable, String s) {
		System.out.println("Meddelande från " + observable + ": " + s);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Meddelande från " + arg0 + ": " + (String)arg1);
	}
}
