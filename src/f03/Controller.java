package f03;
import javax.swing.JFrame;

public class Controller {
	private StringIO inOut;
    private Memo memo = new Memo(this);

    public Controller(StringIO inOut, String title) {
    	this.inOut = inOut;
    	JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(memo);
        frame.pack();
        frame.setVisible(true);
    }

	public void clear() {
		memo.clear();
	}

    public void save() {
    	inOut.write(memo.getText());
    }

    public void load() {
    	memo.setText(inOut.read());
    }
}
