package graphic;

import logic.MyThread;
import logic.MyMouse;

public class Window {

    private MyFrame frame;
    private MyPanel panel;

    public Window(String title, int width, int height, int scale, MyMouse mi, MyThread gm) {
        frame = new MyFrame(title);
        panel = new MyPanel(width, height, scale, gm);

        panel.addMouseListener(mi);
        panel.addMouseMotionListener(mi);
        frame.addContent(panel);
        panel.makePanelFocusable();
    }

    public MyPanel getPanel() { return panel; }
}
