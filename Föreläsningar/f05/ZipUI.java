package f05;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ZipUI extends JPanel {
	private JTextArea taProgress = new JTextArea();
	private ZipController controller;
	private JButton btnZip = new JButton("Zip");
	private JButton btnUnzip = new JButton("Unzip");
	
	public ZipUI(ZipController controller) {
		ActionListener al = new ButtonListener();
		this.controller = controller;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400,600));

		JPanel pnlSouth = new JPanel(new GridLayout(1,3));
		JScrollPane scroll = new JScrollPane(taProgress);
		pnlSouth.add(btnZip);
		pnlSouth.add(btnUnzip);
		add(scroll,BorderLayout.CENTER);
		add(pnlSouth,BorderLayout.SOUTH);
		btnZip.addActionListener(al);
		btnUnzip.addActionListener(al);
	}
	
	public void clear() {
		taProgress.setText("");
	}
	
	public void append(String text) {
		taProgress.append(text + "\n");
	}
	
	private class ButtonListener implements ActionListener {
		private JFileChooser fileChooser = new JFileChooser();
		private FileFilter fileFilter = new ZipFilter();
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnZip) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setFileFilter(null);
				if(fileChooser.showDialog(null,"File to zip")==JFileChooser.APPROVE_OPTION) {
				    controller.zip(fileChooser.getSelectedFile());
				}
			} else if(e.getSource()==btnUnzip) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(fileFilter);
				if(fileChooser.showDialog(null,"File to unzip")==JFileChooser.APPROVE_OPTION) {
				    controller.unzip(fileChooser.getSelectedFile());
				}
			}
			
		}
	}
	
	private class ZipFilter extends FileFilter {
		public boolean accept(File file) {
			return file.getName().toLowerCase().endsWith(".zip");
		}

		@Override
		public String getDescription() {
			return "zip-files";
		}
	}
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, new ZipUI(null));
	}
}
