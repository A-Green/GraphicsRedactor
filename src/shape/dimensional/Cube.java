package shape.dimensional;

import generation.cubeGeneration.CubeGenerator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import transformationTools.dimensionalTrasform.DimensionalTrasform;

import model.Excel;

public class Cube extends DimensionalObject {

	protected ArrayList<Excel> base = new ArrayList<Excel>(
			Arrays.asList(  new Excel(-1,1,-1,Color.black),
							new Excel(1,1,-1,Color.black),
							new Excel(1,-1,-1,Color.black),
							new Excel(-1,-1,-1,Color.black),
							new Excel(-1,1,1,Color.black),
							new Excel(1,1,1,Color.black),
							new Excel(1,-1,1,Color.black),
							new Excel(-1,-1,1,Color.black)));
	protected int side;
	
	public void setSide(int s)
	{
		side = s;
	}
	
	public int getSide()
	{
		return side;
	}
	
	public Cube(int side)
	{
		setSide(side);
		base = DimensionalTrasform.scale(side, base);
		base = DimensionalTrasform.move(2 * side, 2 * side, 0, base);
		setColoredExes();
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel finish) {
		
		int Dx = finish.getX() - start.getX();
		int Dy = finish.getY() - start.getY();
		System.out.println(Dx + "  " + Dy);
		for (Excel ex: coloredEx)
		{
			if (ex.getX() == start.getX() && ex.getY() == start.getY())
			{
				base = DimensionalTrasform.move(Dx, Dy, 1, base);
				setColoredExes();
				break;
			}
		}

	}

	protected void setColoredExes() {
		
		coloredEx = CubeGenerator.generateCube(getSide(), base);
	}

	@Override
	public void rotate(int anlge) {
		
		base = DimensionalTrasform.rotateY(15, base);
		base = DimensionalTrasform.rotateX(15, base);
		base = DimensionalTrasform.rotateZ(15, base);
		
		setColoredExes();
	}

	@Override
	protected void dragg(Excel start, Excel end) {

		
	}

}
