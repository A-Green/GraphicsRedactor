package shape.dimensional;

import generation.cubeGeneration.CubeGenerator;

import java.util.ArrayList;

import model.Excel;

public class Cube extends DimensionalObject {

	public Cube()
	{
		setColoredExes();
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel end) {
		
	}

	@Override
	protected void setColoredExes() {
		
		coloredEx = CubeGenerator.generateCube(10);
	}

	@Override
	public void rotate(int anlge) {

		
	}

	@Override
	protected void dragg(Excel start, Excel end) {

		
	}

}
