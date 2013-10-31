package shape.lines;

import generation.circleGeneration.CircleGenerator;

import java.util.ArrayList;


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
			}
	}

	@Override
	protected void setColoredExes() {
		coloredEx = CircleGenerator.circle(begin, R);
	}

}
