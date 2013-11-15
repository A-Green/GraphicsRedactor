package shape.lines;

import shape.Shape;
import transformationTools.planarTransform.PlanarTransform;

import model.Excel;

public abstract class AbstractLine extends Shape {
	
	protected Excel begin;
	protected Excel end;
	protected Excel center;
	
	public void dragg(Excel start, Excel finish) {
		
		int Dx = finish.getX() - start.getX();
		int Dy = finish.getY() - start.getY();
		
		begin = PlanarTransform.move(Dx, Dy, begin);
		end = PlanarTransform.move(Dx, Dy, end);
	}
	
	public void rotate(int angle, Excel center)
	{
		begin = PlanarTransform.rotate(angle, begin, center);
		end =  PlanarTransform.rotate(angle, end, center);
	}
}
