package shape.lines;

import generation.parabolaGeneration.ParabolaGenerator;

import java.util.ArrayList;

import transformationTools.planarTransform.PlanarTransform;


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

		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel end) {

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

		coloredEx = ParabolaGenerator.parabola(begin, p, size);
	}
	
	public void dragg(Excel start, Excel finish)
	{
		int Dx = finish.getX() - start.getX();
		int Dy = finish.getY() - start.getY();
		
		begin = PlanarTransform.move(Dx,Dy,begin);
	}
	
	@Override
	public void rotate(int angle)
	{
		
	}
}
