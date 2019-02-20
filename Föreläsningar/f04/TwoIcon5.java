package f04;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;

public class TwoIcon5 extends TwoIconLabel implements StartStopListener  {
    private javax.swing.Timer timer;
    
    public TwoIcon5(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

    public TwoIcon5(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

    public TwoIcon5(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
    	ChangeIcon cc = new ChangeIcon();
        timer = new javax.swing.Timer(delay, cc);
    }
    
    public void start() {
    	timer.start();
    }

    public void stop() {
    	timer.stop();
    }

    private class ChangeIcon implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	changeIcon();
        }
    }
}
