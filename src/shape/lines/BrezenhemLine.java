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
		}
	
	if (start.getX() == end.getX() && start.getY() == end.getY())
		{
		end = finish;
		setColoredExes();
		}
		
	}

	@Override
	protected void setColoredExes() {
		coloredEx = LineGenerator.Brezenhem(begin, end);
	}

}
