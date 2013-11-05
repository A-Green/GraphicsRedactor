package transformationTools.planarTransform;

import generation.curveGeneration.Matrix;
import model.Excel;

class Rotate {
	
	// поворачивает относительно центра координат
	public static Excel rotate(int angle, Excel toRotate)
	{
		//матрица поворота
		double[][] m = {
				{ Math.cos(angle * Math.PI/180), Math.sin(angle* Math.PI/180) , 0},
				{(-Math.sin(angle* Math.PI/180)), Math.cos(angle* Math.PI/180), 0},
				{0 , 0, 1}
			};
		Matrix matrix = new Matrix(3,3);
		matrix.setMatrix(m);
			//матрица координат точки	
			double l[][] = {{toRotate.getX()},
							{toRotate.getY()},
							{toRotate.getZ()}
						};
			Matrix line = new Matrix(3,1);
			line.setMatrix(l);
			//Произведение matrix и line
			Matrix multiplied = Matrix.matrixMultiplication(matrix, line);
			
			int x =  (int) Math.round(multiplied.getEl(0, 0)); 
			int y = (int) Math.round(multiplied.getEl(1, 0));
			int z = (int) Math.round(multiplied.getEl(2, 0));
			return new Excel(x,y,z,toRotate.getColor());
	}
}
