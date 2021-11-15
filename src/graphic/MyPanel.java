package graphic;


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import logic.MyThread;

public class MyPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    MyThread thr;

    public MyPanel(int width, int height, int scale, MyThread thr) {
        this.thr = thr;
        Dimension s = new Dimension((int)width * scale, (int) height * scale + 50);
        this.setPreferredSize(s);
        this.setMaximumSize(s);
        this.setMinimumSize(s);
    }

    public void makePanelFocusable() {
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        thr.render(g);
    }

}
