package object;


import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("neighbour")
public class Neighbour {
	
	@Override
	public String toString() {
		return "Neighbour [row1=" + row1 + ", col1=" + col1 + ", row2=" + row2 + ", col2=" + col2 + ", color=" + color
				+ "]";
	}

	@Param(0)
    int row1;

    @Param(1)
    int col1;
    
    @Param(2)
    int row2;

    @Param(3)
    int col2;
    
    @Param(4)
    int color;

	public Neighbour() {
		
	}

	public Neighbour(int row1, int col1, int row2, int col2, int color) {
		this.row1 = row1;
		this.col1 = col1;
		this.row2 = row2;
		this.col2 = col2;
		this.color = color;
	}

	public int getRow1() {
		return row1;
	}

	public void setRow1(int row1) {
		this.row1 = row1;
	}

	public int getCol1() {
		return col1;
	}

	public void setCol1(int col1) {
		this.col1 = col1;
	}

	public int getRow2() {
		return row2;
	}

	public void setRow2(int row2) {
		this.row2 = row2;
	}

	public int getCol2() {
		return col2;
	}

	public void setCol2(int col2) {
		this.col2 = col2;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
    
    
}
