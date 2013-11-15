package transformationTools.dimensionalTrasform;

import generation.curveGeneration.Matrix;

import java.util.ArrayList;

import model.Excel;

class Move {

	public static ArrayList<Excel> move(int a, int b, int c, ArrayList<Excel> toScale)
	{
		 ArrayList<Excel> result = new  ArrayList<Excel>();
		 
		 double matrix[][] = {   {1, 0,0,0},
								 {0, 1,0,0},
								 {0, 0,1,0},
								 {a, b,c,1}
							}; 
		 
		 Matrix scaleMatrix = new Matrix(4,4);
		 scaleMatrix.setMatrix(matrix);
		 
		 for (Excel ex: toScale)
		 {
			 double l[][] = {{ex.getX(), 
							 ex.getY(), 
							 ex.getZ(), 
							 ex.getT()}
					 };
			 Matrix line = new Matrix(1,4);
			 line.setMatrix(l);
			 
			 Matrix multiplied = Matrix.matrixMultiplication(line, scaleMatrix);
			 int x =(int) Math.round(multiplied.getEl(0, 0));
			 int y =(int) Math.round(multiplied.getEl(0, 1));
			 int z =(int) Math.round(multiplied.getEl(0, 2));
			 int t =(int) Math.round(multiplied.getEl(0, 3));
			 
			 result.add(new Excel(x,y,z,t, ex.getColor()));
		 }
		 
		 return result;
	}
}
