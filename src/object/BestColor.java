package object;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("bestColor")
public class BestColor {
	@Param(0)
	int color;
	
	@Param(1)
	int size;

	public BestColor() {
		
	}
	public BestColor(int color) {
		this.color = color;
		this.size = 0;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
