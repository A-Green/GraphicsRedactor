package shape.lines;

import shape.Shape;
import transformationTools.planarTransform.PlanarTransform;

import model.Excel;

public abstract class AbstractLine extends Shape {
	protected Excel begin;
	protected Excel end;
	
	public void dragg(Excel start, Excel finish) {
		
		int Dx = finish.getX() - start.getX();
		int Dy = finish.getY() - start.getY();
		
		begin = PlanarTransform.move(Dx, Dy, begin);
		end = PlanarTransform.move(Dx, Dy, end);
	}
	
	public void rotate(int angle)
	{
		begin = PlanarTransform.rotate(angle, begin);
		end =  PlanarTransform.rotate(angle, end);
	}
}
