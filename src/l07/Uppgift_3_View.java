package l07;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class Uppgift_3_View extends JPanel{

	private JLabel lblTopTitle = new JLabel("Inmatning av matematiska uttryck");
	private Uppgift_3_Controller controller;

	private JRadioButton radioBtnPlus  = new JRadioButton();
	private JRadioButton radioBtnMinus = new JRadioButton();
	private JRadioButton radioBtnMult  = new JRadioButton();
	private JRadioButton radioBtnDiv   = new JRadioButton();
	private ButtonGroup radioBtns = new ButtonGroup();
	
	private JTextField Operand1 = new JTextField();
	private JTextField Operand2 = new JTextField();
	
	public Uppgift_3_View() {
		
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		
		//NORTH
		add(lblTopTitle,BorderLayout.NORTH);
		
		//EAST
		JPanel eastPanel = new JPanel(new GridLayout(4,2));
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

		add(eastPanel,BorderLayout.EAST);
		
		//CENTER
		JPanel centerPanel = new JPanel(new GridLayout(2,1));
		JPanel centerTopPanel = new JPanel(new GridLayout(2,2));
		centerTopPanel.add(new JLabel("Operand 1"));
		centerTopPanel.add(Operand1);
		centerTopPanel.add(new JLabel("Operand 2"));
		centerTopPanel.add(Operand2);
		
	}
	
	public void setController(Uppgift_3_Controller controller) {
		this.controller = controller;
	}

}
