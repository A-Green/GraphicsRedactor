package shape;

import java.util.ArrayList;

import model.Excel;

public abstract class Shape {
	
	protected ArrayList<Excel> coloredEx;
	
	public abstract ArrayList<Excel> getColoredExes();

	public abstract void move(Excel start, Excel end);
	
	protected abstract void setColoredExes();

}
