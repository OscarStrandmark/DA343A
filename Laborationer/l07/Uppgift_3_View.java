package l07;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Uppgift_3_View extends JPanel {

	private JLabel lblTopTitle = new JLabel("Inmatning av matematiska uttryck");

	private JRadioButton radioBtnPlus = new JRadioButton();
	private JRadioButton radioBtnMinus = new JRadioButton();
	private JRadioButton radioBtnMult = new JRadioButton();
	private JRadioButton radioBtnDiv = new JRadioButton();
	private ButtonGroup radioBtns = new ButtonGroup();

	private JButton calcBtn = new JButton("Beräkna");

	private JTextField Operand1 = new JTextField();
	private JTextField Operand2 = new JTextField();

	private JTextArea JTAResult = new JTextArea();

	private Font textFont = new Font("Dialog", Font.PLAIN, 20);
	private Uppgift_3_Controller controller;

	public Uppgift_3_View() {

		init();
		calcBtn.addActionListener(new ButtonListener());
	}

	private void init() {

		setPreferredSize(new Dimension(300, 100));
		lblTopTitle.setFont(textFont);
		radioBtnPlus.setSelected(true);

		setLayout(new BorderLayout());

		// NORTH
		add(lblTopTitle, BorderLayout.NORTH);

		// EAST
		JPanel eastPanel = new JPanel(new GridLayout(4, 2));
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		radioBtns.add(radioBtnPlus);
		radioBtns.add(radioBtnMinus);
		radioBtns.add(radioBtnMult);
		radioBtns.add(radioBtnDiv);

		eastPanel.add(radioBtnPlus);
		eastPanel.add(new JLabel("+"));

		eastPanel.add(radioBtnMinus);
		eastPanel.add(new JLabel("-"));

		eastPanel.add(radioBtnMult);
		eastPanel.add(new JLabel("*"));

		eastPanel.add(radioBtnDiv);
		eastPanel.add(new JLabel("/"));

		add(eastPanel, BorderLayout.EAST);

		// CENTER
		JPanel centerPanel = new JPanel(new GridLayout(2, 1));
		JPanel centerTopPanel = new JPanel(new GridLayout(2, 2));
		centerTopPanel.setBorder(BorderFactory.createTitledBorder("Operander"));

		centerTopPanel.add(new JLabel("Operand 1"));
		centerTopPanel.add(Operand1);
		centerTopPanel.add(new JLabel("Operand 2"));
		centerTopPanel.add(Operand2);
		centerPanel.add(centerTopPanel);
		centerPanel.add(calcBtn);

		add(centerPanel, BorderLayout.CENTER);

		// SOUTH
		add(JTAResult, BorderLayout.SOUTH);
	}

	public void setController(Uppgift_3_Controller controller) {
		this.controller = controller;
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == calcBtn) {

				int o1 = 0;
				int o2 = 0;

				try {
					o1 = Integer.parseInt(Operand1.getText());
					o2 = Integer.parseInt(Operand2.getText());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				String operand = "";

				if (radioBtnPlus.isSelected()) {
					operand = "+";
				} else if (radioBtnMinus.isSelected()) {
					operand = "-";
				} else if (radioBtnMult.isSelected()) {
					operand = "*";
				} else if (radioBtnDiv.isSelected()) {
					operand = "/";
				}
				
				controller.calculate(o1,o2,operand);
			}
		}
	}
}
