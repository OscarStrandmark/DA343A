package f04;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;

public class TwoIcon1 extends TwoIconLabel {
    private javax.swing.Timer timer;

    public TwoIcon1(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

    public TwoIcon1(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

    public TwoIcon1(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
    	ChangeIcon cc = new ChangeIcon();
        timer = new javax.swing.Timer(delay, cc);
    }
    
    public void start() {
        timer.start();
    }
    
    private class ChangeIcon implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	changeIcon(); // anrop till metoden changeIcon() vilken TwoIcon1 ärvt från TwoIconLabel
        }
    }
}
