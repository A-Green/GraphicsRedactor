package generation.cubeGeneration;

import generation.lineGeneration.LineGenerator;
import java.util.ArrayList;

import model.Excel;

public class CubeGenerator {
		
	public static ArrayList<Excel> generateCube(int side, ArrayList<Excel> base, Excel center)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		result.addAll(LineGenerator.Brezenhem(base.get(0), base.get(1)));
		result.addAll(LineGenerator.Brezenhem(base.get(1), base.get(2)));
		result.addAll(LineGenerator.Brezenhem(base.get(2), base.get(3)));
		result.addAll(LineGenerator.Brezenhem(base.get(3), base.get(0)));
		
		result.addAll(LineGenerator.Brezenhem(base.get(4), base.get(5)));
		result.addAll(LineGenerator.Brezenhem(base.get(5), base.get(6)));
		result.addAll(LineGenerator.Brezenhem(base.get(6), base.get(7)));
		result.addAll(LineGenerator.Brezenhem(base.get(7), base.get(4)));
		
		result.addAll(LineGenerator.Brezenhem(base.get(0), base.get(4)));
		result.addAll(LineGenerator.Brezenhem(base.get(1), base.get(5)));
		result.addAll(LineGenerator.Brezenhem(base.get(2), base.get(6)));
		result.addAll(LineGenerator.Brezenhem(base.get(3), base.get(7)));	

		return result;
	}

}
