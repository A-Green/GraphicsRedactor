package transformationTools;

import generation.curveGeneration.Matrix;

import java.awt.Color;
import java.util.ArrayList;

import model.Excel;

public class PlanarTransform{	

	public static ArrayList<Excel> move(int a, int b, ArrayList<Excel> tranformated)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		double[][] m = {
				{1,0,a},
				{0,1,b},
				{0,0,1}
			};
	Matrix matrix = new Matrix(3,3);
	matrix.setMatrix(m);

	for(Excel ex: tranformated )
	{	
		double l[] = {ex.getX(), ex.getY(), ex.getZ()};

		Matrix line = new Matrix(3,1);
		line.fillingMatrix(l);
		Matrix multiplyed = Matrix.matrixMultiplication(matrix, line);
		
		int x = (int) multiplyed.getEl(0, 0);
		int y = (int) multiplyed.getEl(1, 0);
		int z = (int) multiplyed.getEl(2, 0);
		
		ex.setX(x);
		ex.setY(y);
		ex.setZ(z);
	}
		return tranformated;
	}
}
