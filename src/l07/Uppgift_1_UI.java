package l07;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Uppgift_1_UI extends JPanel implements ActionListener {

	InetAddress localAddress;

	private JLabel myInfo = new JLabel();

	private JTextField jtf = new JTextField();

	private JLabel hostIP = new JLabel("IP: -");

	public Uppgift_1_UI() {

		init();
		
		try {
			localAddress = InetAddress.getLocalHost();
			myInfo.setText("IP=" + localAddress.getHostAddress() + "," + "Name=" + localAddress.getHostName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jtf.addActionListener(this);
		
	}

	private void init() {
		setLayout(new BorderLayout());

		add(myInfo, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new GridLayout(1, 2));

		centerPanel.add(new JLabel("Host name:"));
		centerPanel.add(jtf);

		add(centerPanel, BorderLayout.CENTER);
		
		add(hostIP,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			InetAddress target = InetAddress.getByName(jtf.getText());
			hostIP.setText("IP: " + target.getHostAddress());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
