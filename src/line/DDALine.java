package line;

import java.util.ArrayList;

import lineGeneration.LineGenerator;
import model.Excel;

public class DDALine extends AbstractLine {

	public DDALine(Excel ex1, Excel ex2)
	{
		begin = ex1;
		end = ex2;
		this.setColoredExes();
	}

	@Override
	public ArrayList<Excel> getColoredExes() {
		return coloredEx;
	}
	@Override
	public void move(Excel start, Excel finish) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		coloredEx = LineGenerator.DDA(begin, end);
	}
		
}
