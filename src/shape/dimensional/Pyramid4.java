package shape.dimensional;

import generation.PyramidGenerator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import model.Excel;
import transformationTools.dimensionalTrasform.DimensionalTrasform;

public class Pyramid4 extends DimensionalObject{

	protected ArrayList<Excel> base = new ArrayList<Excel>(
			Arrays.asList(  new Excel(-1,-1,-1,Color.black),
							new Excel(1,-1,-1,Color.black),
							new Excel(1,1,-1,Color.black),
							new Excel(-1,1,-1,Color.black),
							new Excel(0,0,1,Color.black)));
	protected int side;
	
	protected int Dx;
	protected int Dy;
	
	public void setSide(int s)
	{
		side = s;
	}
	
	public int getSide()
	{
		return side;
	}
	
	public Pyramid4(int side)
	{
		setSide(side);
		base = DimensionalTrasform.scale(side, base);
		coloredEx = new ArrayList<Excel>();
		setColoredExes();
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel finish) {
		
		for(Excel ex: coloredEx)
		{
			if (ex.getX() == start.getX() && ex.getY() == start.getY())
			{
				Dx += finish.getX() - start.getX();
				Dy += finish.getY() - start.getY();
				setColoredExes();
				 return;
			}
		}

	}

	protected void setColoredExes() {
		

		 ArrayList<Excel> temp = PyramidGenerator.generatePyramid4(side, base);
		 
		 coloredEx.clear();
		 
		 for(Excel ex: temp)
		 {
			 ex.setX(ex.getX() + Dx);
			 ex.setY(ex.getY() + Dy);
			 coloredEx.add(ex);
		 }
		 
	}

	@Override
	public void rotate(int angle) {
		
		base = DimensionalTrasform.rotateX(angle, base);
		setColoredExes();
		base = DimensionalTrasform.rotateY(angle, base);
		setColoredExes();
		base = DimensionalTrasform.rotateZ(angle, base);	
		setColoredExes();
	}

	@Override
	protected void dragg(Excel start, Excel end) {

		
	}

	@Override
	public void rotateX(int angle) {
		
		base = DimensionalTrasform.rotateX(angle, base);
		setColoredExes();
		
	}

	@Override
	public void rotateY(int angle) {
		
		base = DimensionalTrasform.rotateY(angle, base);
		setColoredExes();
	}

	@Override
	public void rotateZ(int angle) {
		
		base = DimensionalTrasform.rotateZ(angle, base);	
		setColoredExes();
		
	}


}
