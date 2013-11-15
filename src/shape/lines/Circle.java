package shape.lines;

import generation.circleGeneration.CircleGenerator;

import java.util.ArrayList;

import transformationTools.planarTransform.PlanarTransform;


import model.Excel;

public class Circle extends AbstractLine {

	protected int R;
	
	public Circle(Excel ex, int R)
	{
		begin = ex;
		this.R = R;
		setColoredExes();
	}
	
	@Override
	public ArrayList<Excel> getColoredExes() {
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel end) {
		if (start.getX() == begin.getX() && start.getY() == begin.getY())			
			{
			begin = end;
			setColoredExes();
			return;
			}
		
		for(Excel ex: coloredEx)
		{
			if (ex.equals(start))
			{
				dragg(start, end);
				setColoredExes();	
				break;
			}
		}

	}

	@Override
	protected void setColoredExes() {
		coloredEx = CircleGenerator.circle(begin, R);
	}
	
	@Override
	public void dragg(Excel start, Excel finish)
	{
		int Dx = finish.getX() - start.getX();
		int Dy = finish.getY() - start.getY();
		
		begin = PlanarTransform.move(Dx, Dy, begin);
		
	}
	
	@Override
	public void rotate(int angle)
	{

	}

}
