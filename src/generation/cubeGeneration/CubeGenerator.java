package generation.cubeGeneration;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import model.Excel;

public class CubeGenerator {
	
	private static ArrayList<Excel> base = new ArrayList<Excel>(
			Arrays.asList(new Excel(0, 0, 0, Color.black),
						  new Excel(1, 0, 0, Color.black),
						  new Excel(1, 1, 0, Color.black),
						  new Excel(0, 1, 0, Color.black),
						  new Excel(0, 0, 1, Color.black),
						  new Excel(1, 0, 1, Color.black),
						  new Excel(1, 1, 1, Color.black),
						  new Excel(0, 1, 1, Color.black)
			));
	
	public static ArrayList<Excel> generateCube(int side)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		result = base;
		return result;
	}

}
