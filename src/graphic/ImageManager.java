package graphic;

import java.awt.image.BufferedImage;

public class ImageManager {

    private BufferedImage yellowCell = null;
    private BufferedImage redCell = null;
    private BufferedImage greenCell = null;
    private BufferedImage blueCell = null;
    private BufferedImage selectedCell = null;
    private BufferedImage blackScreen = null;
    private BufferedImage youWin = null;
    private BufferedImage youLose = null;

    BufferedImageLoader loader = new BufferedImageLoader();

    public ImageManager() {
        yellowCell = loader.loadBufferedImage("/res/yellow.png");
        redCell = loader.loadBufferedImage("/res/red.png");
        greenCell = loader.loadBufferedImage("/res/green.png");
        blueCell = loader.loadBufferedImage("/res/blue.png");
        selectedCell = loader.loadBufferedImage("/res/selected.png");
        blackScreen = loader.loadBufferedImage("/res/blackScreen.jpg");
        youWin = loader.loadBufferedImage("/res/win.png");
        youLose = loader.loadBufferedImage("/res/lose.jpg");
        
    }

    public BufferedImage getYellowCell() {
        return yellowCell;
    }

    public BufferedImage getRedCell() {
        return redCell;
    }

    public BufferedImage getGreenCell() {
        return greenCell;
    }


    public BufferedImage getBlueCell() {
        return blueCell;
    }

    public BufferedImage getSelectedCell() { 
    	return selectedCell; }
    
    public BufferedImage getBlackScreen() {
        return blackScreen;
    }

	public BufferedImage getYouWin() {
		return youWin;
	}

	public BufferedImage getYouLose() {
		return youLose;
	}
}
