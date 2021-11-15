package object;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cell")
public class Cell {

    @Param(0)
    int row;

    @Param(1)
    int col;

    @Param(2)
    int color;
    
    @Param(3)
    int status;

    @Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", color=" + color + ", status=" + status + "]";
	}

	public Cell() {
       
    }

    public Cell(int color, int status) {
        this.row = 0;
        this.col = 0;
        this.color = color;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
