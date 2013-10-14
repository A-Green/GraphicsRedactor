package line;

import java.util.ArrayList;

import parabola.ParabolaGenerator;

import model.Excel;

public class Parabola extends AbstractLine{

	protected double p;
	protected int size;
	
	public Parabola(Excel ex, double p, int size)
	{
		begin = ex;
		this.p = p;
		this.size = size;
		setColoredExes();
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		// TODO Auto-generated method stub
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel end) {
		// TODO Auto-generated method stub
		if (start.getX() == begin.getX() && start.getY() == begin.getY())			
			{
			begin = end;
			setColoredExes();
			}
	}
	@Override
	protected void setColoredExes() {
		// TODO Auto-generated method stub
		coloredEx = ParabolaGenerator.parabola(begin, p, size);
	}


}
