package shape.dimensional;

import java.util.ArrayList;

import model.Excel;

import shape.Shape;

public abstract class DimensionalObject extends Shape {

	protected ArrayList<Excel> base;
	
	protected Excel center;
	
	public abstract void rotateX(int angle);

	public abstract void rotateY(int angle);

	public abstract void rotateZ(int angle);
	
	public abstract void rotate(int angle);



}


