package object;

import java.util.Random;
import java.util.Vector;

public class Matrix {

    private static int dim = 5;

    private Vector<Vector<Cell>> matrix;

    Vector<Cell> cellSelected;

    private int score;
    private int moves;
    private int level;

    private static final Random RANDOM = new Random();
    
    public Matrix(){
        matrix = new Vector<>();
        cellSelected = new Vector<>();
        score = 0;
        moves=5;
        level=1;
        for (int i = 0; i<dim; i++){
            Vector<Cell> col = new Vector<>();
            for (int j = 0; j<dim; j++) {
                Cell c = new Cell(this.randomInt(), 0);
                col.add(c);
            }
            matrix.add(col);
        }
        refreshCellPosition();
    }
    public int randomInt(){
    	return RANDOM.nextInt(4);
    }
    public void refreshMatrix() {
        for (int i = 0; i < dim; i++) {
            matrix.get(i).removeIf(cell -> (cell.getStatus()==2));
            if (matrix.get(i).size() < dim) {
                int cont = dim - matrix.get(i).size();
                while (cont != 0) {
                    Cell c = new Cell(this.randomInt(), 0);
                    matrix.get(i).add(0, c);
                    cont--;
                }
            }
        }
        refreshCellPosition();
    }


    public void refreshCellPosition() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                matrix.get(j).get(i).setCol(j);
                matrix.get(j).get(i).setRow(i);
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print("[" + matrix.get(j).get(i).getRow() + "," + matrix.get(j).get(i).getCol() + "] ");
            }

            for (int j = 0; j < dim; j++) {
                System.out.print(matrix.get(j).get(i).getStatus() + " ");
            }

            for (int j = 0; j < dim; j++) {
                System.out.print(matrix.get(j).get(i).getColor() + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void setElementStatus(int row, int col, int st){
        matrix.get(col).get(row).setStatus(st);
    }

    public int getElementStatus(int row, int col) { return matrix.get(col).get(row).getStatus(); }

    public Cell getElement(int row, int col) { return matrix.get(col).get(row); }

    public int getDim() {
        return dim;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public Vector<Cell> getElementOfCellSelected() {
        return cellSelected;
    }

    public void addCellSelected(Cell c) {
        cellSelected.add(c);
    }

    public Cell getElementOfCellSelected(int i) {
        return cellSelected.get(i);
    }

    public void clearCellSelected() {
        cellSelected.clear();
    }
	public int getMoves() {
		return moves;
	}
	public void setMoves(int moves) {
		this.moves -= moves;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level += level;
	}
	public void resetMoves(int lv) {
		this.moves=5*lv;
		
	}
}
