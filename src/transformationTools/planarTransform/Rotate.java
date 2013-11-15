package transformationTools.planarTransform;

import generation.curveGeneration.Matrix;
import model.Excel;

class Rotate {
	
	// поворачивает относительно центра координат
	public static Excel rotate(int angle, Excel toRotate, Excel center)
	{
		System.out.println(center);
		//матрица поворота
		double[][] m = {
				{ Math.cos(angle * Math.PI/180), Math.sin(angle* Math.PI/180) , 0},
				{(-Math.sin(angle* Math.PI/180)), Math.cos(angle* Math.PI/180), 0},
				{0 , 0, 1}
			};
		Matrix matrix = new Matrix(3,3);
		matrix.setMatrix(m);
		
		//матрица переноса начала координат в центр отрезка
		double[][] to = {
				{1,0,0},
				{0,1,0},
				{-center.getX(), -center.getY(),1}
		};
		
		Matrix toCenter = new Matrix(3,3);
		toCenter.setMatrix(to);
		
		// матрица возврата центра координат
		double[][] from = {
				{1,0,0},
				{0,1,0},
				{center.getX(), center.getY(),1}
		};
		
		Matrix fromCenter = new Matrix(3,3);
		fromCenter.setMatrix(from);
		
		// матрица координат точки
		double l[][] = {{toRotate.getX(),
						 toRotate.getY(),
						 toRotate.getZ()}
						};
		Matrix line = new Matrix(1,3);
		line.setMatrix(l);
		//перенос начала координат в рассматриваемую точку
		Matrix multiplied = Matrix.matrixMultiplication(line, toCenter);
		//вращение относительно рассмариваемой точки
		multiplied = Matrix.matrixMultiplication(multiplied, matrix);
		//возврат центра координат
		multiplied = Matrix.matrixMultiplication(multiplied, fromCenter);
		//новые координаты точки
			int x =  (int) Math.round(multiplied.getEl(0, 0)); 
			int y = (int) Math.round(multiplied.getEl(0, 1));
			int z = (int) Math.round(multiplied.getEl(0, 2));
		
			return new Excel(x,y,z,toRotate.getColor());
	}
}
