package logic;

import graphic.ImageManager;
import object.Matrix;
import java.awt.*;

public class GameLogic{

    private static Matrix matrix;

    private static MyMouse mouse;

    private static int best=-1;
    
    private int lev = 50;
    
    private static DLVSolver solver;

    private static ImageManager tex;

     public GameLogic() {
        matrix = new Matrix();
        mouse = new MyMouse(this);
        solver = new DLVSolver(matrix);
        tex = new ImageManager();
    }

    public static void render(Graphics g) {
   
        for (int col = 0; col < matrix.getDim(); col++) {
            for (int row = 0; row < matrix.getDim(); row++) {
                if (matrix.getElement(col, row).getColor()==0)
                    g.drawImage(tex.getBlueCell(), row * 100, col * 100, 100, 100, null);
                if (matrix.getElement(col, row).getColor()==1)
                    g.drawImage(tex.getYellowCell(), row * 100, col * 100, 100, 100, null);
                if (matrix.getElement(col, row).getColor()==2)
                    g.drawImage(tex.getRedCell(), row * 100, col * 100, 100, 100, null);
                if (matrix.getElement(col, row).getColor()==3)
                    g.drawImage(tex.getGreenCell(), row * 100, col * 100, 100, 100, null);

                if (matrix.getElement(col,row).getStatus()==1)
                    g.drawImage(tex.getSelectedCell(), row * 100, col * 100, 100, 100, null);
            }
        }

        g.drawString("Level: " + matrix.getLevel(), 30, 525);
        g.drawString("Moves: " + matrix.getMoves(), 80, 525);
        g.drawString("Score: " + matrix.getScore(), 150, 525);
        g.drawString("Hint: ", 335, 525);
        
        if(best==0) {
        	g.drawImage(tex.getBlueCell(), 360, 512, 20, 20, null);
        }
        if(best==1) {
        	g.drawImage(tex.getYellowCell(), 360, 512, 20, 20, null);
        }
        if(best==2) {
        	g.drawImage(tex.getRedCell(), 360, 512, 20, 20, null);
        }
        if(best==3) {
        	g.drawImage(tex.getGreenCell(), 360, 512, 20, 20, null);
        }
        if(matrix.getMoves()==0) {
        	g.drawImage(tex.getYouLose(), 0, 0, 500, 500, null);
        }
        if(matrix.getScore()>=900) {
        	g.drawImage(tex.getYouWin(), 0, 0, 500, 500, null);
        }
    }
    
    public void noMove() {
    	if(solver.alone()) {
    		matrix.resetMoves(0);
    	}
    }
    
    public void doMove() {
        if (matrix.getElementStatus(mouse.getY(), mouse.getX())!=1) {
            matrix.setElementStatus(mouse.getY(), mouse.getX(), 1);
            matrix.addCellSelected(matrix.getElement(mouse.getY(), mouse.getX()));
        }
    }

    public void checkMove() {
        matrix.printMatrix();
        if(solver.checkSelected(matrix.getElementOfCellSelected())) {    
        	for (int i = 0; i < matrix.getElementOfCellSelected().size(); i++) {
                matrix.setElementStatus(matrix.getElementOfCellSelected(i).getRow(), matrix.getElementOfCellSelected(i).getCol(), 2);
            }

            matrix.setScore(matrix.getElementOfCellSelected().size()*10);
            matrix.setMoves(1);
            
            if(matrix.getScore()>=lev) {
            	matrix.setLevel(1);
            	matrix.resetMoves(matrix.getLevel());
            	lev *= matrix.getLevel();
            }
            
            
        } else {
            for (int i = 0; i < matrix.getElementOfCellSelected().size(); i++) {
                matrix.setElementStatus(matrix.getElementOfCellSelected(i).getRow(), matrix.getElementOfCellSelected(i).getCol(), 0);
            }
        }
        matrix.printMatrix();
        matrix.refreshMatrix();
        
        matrix.clearCellSelected();
        solver.refreshProgramFact();
        best=solver.bestMove();
    }

    public static MyMouse getMouseInput() { return mouse; }

}
