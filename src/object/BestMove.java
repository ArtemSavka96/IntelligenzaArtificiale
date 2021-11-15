package object;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("bestMove")
public class BestMove {
	
	@Param(0)
	int color;
	
	public BestMove() {	}
	
	public BestMove(BestMove b) {
		
	}
	
	@Override
	public String toString() {
		return "BestMove [color=" + color + "]";
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public BestMove(int color) {
		super();
		this.color = color;
	}
	
	//public String getStringStr() {
		//return this.color.name();
		
	//}
}
