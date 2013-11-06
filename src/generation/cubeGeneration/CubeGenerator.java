package generation.cubeGeneration;

import generation.lineGeneration.LineGenerator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import transformationTools.dimensionalTrasform.DimensionalTrasform;

import model.Excel;

public class CubeGenerator {
		
	public static ArrayList<Excel> generateCube(int side, ArrayList<Excel> base)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();

	//	base = DimensionalTrasform.scale(side, base);
	//	base = DimensionalTrasform.move(2 * side, 2 * side, 0, base);
		
	//	base = DimensionalTrasform.rotateY(15, base);
	//	base = DimensionalTrasform.rotateX(15, base);
	//	base = DimensionalTrasform.rotateZ(15, base);
		result.addAll(LineGenerator.WuAlgorithm(base.get(0), base.get(1)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(1), base.get(2)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(2), base.get(3)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(3), base.get(0)));
		
		result.addAll(LineGenerator.WuAlgorithm(base.get(4), base.get(5)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(5), base.get(6)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(6), base.get(7)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(7), base.get(4)));
		
		result.addAll(LineGenerator.WuAlgorithm(base.get(0), base.get(4)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(1), base.get(5)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(2), base.get(6)));
		result.addAll(LineGenerator.WuAlgorithm(base.get(3), base.get(7)));
		

		return result;
	}

}
