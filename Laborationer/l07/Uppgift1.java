package l07;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.*;

public class Uppgift1 extends JPanel implements ActionListener {
	
	private JLabel lblNorth = new JLabel();
	
	private JLabel lblCenter = new JLabel("Host name:");
	private JTextField JTF = new JTextField();
	
	private JLabel lblSouth = new JLabel("Host ip: -");
	
	public Uppgift1() {

		super.setLayout(new BorderLayout());
		
		
		
		//North
		add(lblNorth,BorderLayout.NORTH);

		try {
			InetAddress address = InetAddress.getLocalHost();
			lblNorth.setText("IP: " + address.getHostAddress() +" , " + "Name: " + address.getHostName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Center
		JPanel panCenter = new JPanel(new GridLayout(1,2));
		panCenter.add(lblCenter);
		panCenter.add(JTF);
		add(panCenter, BorderLayout.CENTER);
		JTF.addActionListener(this);
		
		//South
		add(lblSouth, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			InetAddress address = InetAddress.getByName(JTF.getText());
			lblSouth.setText("Host ip: " + address.getHostAddress());
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		JFrame w = new JFrame();
		w.add(new Uppgift1());
		w.setVisible(true);
	}
}
