package transformationTools.planarTransform;

import generation.curveGeneration.Matrix;

import model.Excel;

class Move {

	public static Excel move(int a, int b, Excel toMove)
	{
		double[][] m = {
				{1,0,a},
				{0,1,b},
				{0,0,1}
			};
	Matrix matrix = new Matrix(3,3);
	matrix.setMatrix(m);

		double l[] = {toMove.getX(), toMove.getY(), toMove.getZ()};

		Matrix line = new Matrix(3,1);
		line.fillingMatrix(l);

		Matrix multiplyed = Matrix.matrixMultiplication(matrix, line);
		
		int x = (int) multiplyed.getEl(0, 0);
		int y = (int) multiplyed.getEl(1, 0);
		int z = (int) multiplyed.getEl(2, 0);
		return new Excel(x,y,z, toMove.getColor());
	}
}
