package shape.lines;

import generation.lineGeneration.LineGenerator;

import java.util.ArrayList;

import model.Excel;

public class BrezenhemLine extends AbstractLine{

	public BrezenhemLine(Excel ex1, Excel ex2) {
		begin = ex1;
		end = ex2;
		setColoredExes();
	}

	@Override
	public ArrayList<Excel> getColoredExes() {
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel finish) {
		if (start.getX() == begin.getX() && start.getY() == begin.getY())			
		{
			begin = finish;
			setColoredExes();
			return;
		}
	
	if (start.getX() == end.getX() && start.getY() == end.getY())
		{
		end = finish;
		setColoredExes();
		return;
		}
	

	for(Excel ex: coloredEx)
	{
		if (ex.equals(start))
		{
			dragg(start, finish);
			setColoredExes();	
			break;
		}
	}

	}

	@Override
	protected void setColoredExes() {
		coloredEx = LineGenerator.Brezenhem(begin, end);
	}
	
	@Override
	public void rotate(int angle)
	{
		super.rotate(angle);
		setColoredExes();
	}


}
