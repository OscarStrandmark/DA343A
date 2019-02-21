package l07.Uppgift4.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class UI extends JPanel {

	private Controller controller;
	
	private JRadioButton btnPlus = new JRadioButton();
	private JRadioButton btnMinu = new JRadioButton();
	private JRadioButton btnMult = new JRadioButton();
	private JRadioButton btnDivi = new JRadioButton();
	
	private ButtonGroup grp = new ButtonGroup();
	
	private JTextField JTFOp1 = new JTextField();
	private JTextField JTFOp2 = new JTextField();
	
	private JButton btnCalc = new JButton("Beräkna");
	
	private JTextArea JTA = new JTextArea();
	
	public UI() {
		
		super.setLayout(new GridLayout(3,1));
		
		add(new JLabel("Inmatning av matematiska uttryck"));
		
		JPanel centerPanel = new JPanel(new GridLayout(1,2));
		
		JPanel centerLeftPanel = new JPanel(new GridLayout(2,2));
		
		JPanel centerLeftTopPanel = new JPanel(new GridLayout(2,2));
		
		centerLeftTopPanel.add(new JLabel("Operand 1:"));
		centerLeftTopPanel.add(JTFOp1);
		centerLeftTopPanel.add(new JLabel("Operand 2:"));
		centerLeftTopPanel.add(JTFOp2);
		centerLeftTopPanel.setBorder(BorderFactory.createTitledBorder("Operander"));
		
		centerLeftPanel.add(centerLeftTopPanel);
		centerLeftPanel.add(btnCalc);
		centerPanel.add(centerLeftPanel);
		
		JPanel centerRightPanel = new JPanel(new GridLayout(4,2));
		
		grp.add(btnPlus);
		grp.add(btnMinu);
		grp.add(btnMult);
		grp.add(btnDivi);
		btnPlus.setSelected(true);
		
		centerRightPanel.add(btnPlus);
		centerRightPanel.add(new JLabel("+"));
		centerRightPanel.add(btnMinu);
		centerRightPanel.add(new JLabel("-"));
		centerRightPanel.add(btnMult);
		centerRightPanel.add(new JLabel("*"));
		centerRightPanel.add(btnDivi);
		centerRightPanel.add(new JLabel("/"));
		
		centerRightPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		centerPanel.add(centerRightPanel);
		
		add(centerPanel);
		
		JTA.setEditable(false);
		add(JTA);
		
		btnCalc.addActionListener(new ButtonListener());
	}
	
	public void addResponse(String s) {
		JTA.setText(s);
	}
	public void addController(Controller controller) {
		this.controller = controller;
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			char op = 'x';

			if(btnPlus.isSelected()) {
				op = '+';
			}
			if(btnMinu.isSelected()) {
				op = '-';
			}
			if(btnMult.isSelected()) {
				op = '*';
			}
			if(btnDivi.isSelected()) {
				op = '/';
			}

			controller.newExpression(JTFOp1.getText(),JTFOp2.getText(),op);
		}		
	}
}
