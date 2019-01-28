package f03;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Memo extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea();
	private Controller controller;
	
    public Memo(Controller cont) {
    	this.controller = cont;
    	setLayout(new BorderLayout());
        JPanel pnlButtons = new JPanel(new GridLayout(1, 3));
        JButton btnWrite = new JButton("Save");
        JButton btnRead = new JButton("Load");
        JButton btnClear = new JButton("Clear");
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scroll.setPreferredSize(new Dimension(400,500));
        btnWrite.addActionListener(new SAL());
        btnRead.addActionListener(new HAL());
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		controller.clear();
        	}
        });
        pnlButtons.add(btnWrite);
        pnlButtons.add(btnRead);
        pnlButtons.add(btnClear);
        add(scroll, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);
    }
    
    public String getText() {
    	return textArea.getText();
    }
    
    public void setText(String txt) {
    	textArea.setText(txt);
    }
    
    public void append(String txt) {
    	textArea.append(txt);
    }
    
    public void clear() {
    	textArea.setText("");
    }
    
    private class SAL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	controller.save();
        }
    }

    private class HAL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            controller.load();
        }
    }
}
