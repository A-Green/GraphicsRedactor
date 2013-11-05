package transformationTools.planarTransform;

import generation.curveGeneration.Matrix;

import model.Excel;

class Move {

	//Перемещение в однородной системе координат
	public static Excel move(int a, int b, Excel toMove)
	{
		//матрица перемещения
		double[][] m = {
				{1,0,a},
				{0,1,b},
				{0,0,1}
			};
	Matrix matrix = new Matrix(3,3);
	matrix.setMatrix(m);
		//матрица координат точки
		double l[] = {toMove.getX(), toMove.getY(), toMove.getZ()};

		Matrix line = new Matrix(3,1);
		line.fillingMatrix(l);
		//произведение матриц, матрица, содержащая новые координаты точки
		Matrix multiplyed = Matrix.matrixMultiplication(matrix, line);
		
		int x = (int) multiplyed.getEl(0, 0);
		int y = (int) multiplyed.getEl(1, 0);
		int z = (int) multiplyed.getEl(2, 0);
		return new Excel(x,y,z, toMove.getColor());
	}
}
