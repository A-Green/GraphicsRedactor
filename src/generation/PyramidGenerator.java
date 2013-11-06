package generation;

import generation.lineGeneration.LineGenerator;

import java.util.ArrayList;

import model.Excel;

public class PyramidGenerator {
	
	public static ArrayList<Excel> generatePyramid4(int side, ArrayList<Excel> base)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();

		result.addAll(LineGenerator.Brezenhem(base.get(0), base.get(1)));
		result.addAll(LineGenerator.Brezenhem(base.get(1), base.get(2)));
		result.addAll(LineGenerator.Brezenhem(base.get(2), base.get(3)));
		result.addAll(LineGenerator.Brezenhem(base.get(3), base.get(0)));
		
		result.addAll(LineGenerator.Brezenhem(base.get(0), base.get(4)));
		result.addAll(LineGenerator.Brezenhem(base.get(1), base.get(4)));
		result.addAll(LineGenerator.Brezenhem(base.get(2), base.get(4)));
		result.addAll(LineGenerator.Brezenhem(base.get(3), base.get(4)));

		return result;
	}

}
