package f07.udpclient1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ClientPanel extends JPanel implements ActionListener {
	private ClientController controller;
	private JTextArea taResponse = new JTextArea();
	private JButton btnPut = new JButton("PUT");
	private JButton btnGet = new JButton("GET");
	private JButton btnClr = new JButton("CLR");
	private JButton btnQuit = new JButton("Avsluta");
	
	public ClientPanel(ClientController controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		taResponse.setPreferredSize(new Dimension(200,300));
		add(menu(),BorderLayout.NORTH);
		add(new JScrollPane(taResponse),BorderLayout.CENTER);
	}
	
	private JPanel menu() {
		JPanel panel = new JPanel(new GridLayout(4,1));
		panel.add(btnPut);
		panel.add(btnGet);
		panel.add(btnClr);
		panel.add(btnQuit);
		btnPut.addActionListener(this);
		btnGet.addActionListener(this);
		btnClr.addActionListener(this);
		btnQuit.addActionListener(this);
		return panel;
	}
	
	public void addResponse(final String response) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		        taResponse.append(response+"\n");
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnPut) {
			taResponse.setText("PUT\n");
			controller.sendMessage( "PUT" + JOptionPane.showInputDialog( "Skriv text att spara" ) );
		} else if(e.getSource()==btnGet) {
			taResponse.setText("GET\n");
			controller.sendMessage( "GET" );
		} else if(e.getSource()==btnClr) {
			taResponse.setText("CLR\n");
			controller.sendMessage( "CLR" );
		} else if(e.getSource()==btnQuit) {
			controller.exit();
		}
	}
}
