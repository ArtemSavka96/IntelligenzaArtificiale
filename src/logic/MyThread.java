package logic;

import graphic.Window;
import java.awt.Graphics;

public class MyThread implements Runnable {

    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;

    private Window window;
    private GameLogic gl;

    private int FPS = 60;
    @SuppressWarnings("unused")
    private long targetTime = 1000 / FPS;

    public void start() {
        gl = new GameLogic();
        window = new Window("TwoDots", GameSettings.WIDTH, GameSettings.HEIGHT, GameSettings.SCALE, GameLogic.getMouseInput(), this);
        this.run();
    }

    @Override
    public void run() {
        running = true;
        boolean render = false;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime =  0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;
        
        while(running)
        {
        	render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP){
            
                unprocessedTime -= UPDATE_CAP;
                render = true;    
            }
            
            if (frameTime >= 1.0){
           
                frameTime = 0;
                fps = frames;
                frames = 0;
                System.out.println("FPS: " + fps);
            }
            if (render) {
                window.getPanel().repaint();
                frames++;
            }
        }
    }


    public void render(Graphics g) {
        gl.render(g);
    }

    public static void main(String[] argl) {
        MyThread thr = new MyThread();
        thr.start();

    }

}
