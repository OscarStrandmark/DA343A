package l02;

import javax.swing.JOptionPane;

public class Exercise8 {
	public static void main(String[] args) {
        Exercise6 e6 = new Exercise6("C:\\Users\\oscar\\git\\DA343A\\src\\l02\\files\\SkSvEn.txt");
		Exercise7 e7 = new Exercise7("C:\\Users\\oscar\\git\\DA343A\\src\\l02\\files\\SkSvEn.txt");

		String menuText = "Välj ett alternativ. \n \n0. Avsluta\n1. översätt ett ord";
		
		while(true) {
			String choice = JOptionPane.showInputDialog(menuText);
			int res = 0;
			
			try {
				res = Integer.parseInt(choice);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			if(res == 0) {
				System.exit(0);
			}
			if(res == 1) {
				String response = JOptionPane.showInputDialog("Ange engelskt ord att översätta till skånska.");
				String skö = e7.translate(e6.translate(response));
				if(skö == null) {
					JOptionPane.showMessageDialog(null, "Ordet finns inte i ordlistan");
				} else {
					JOptionPane.showMessageDialog(null, response + " = " + skö);
				}
			}
		}
	}
}
