package transformationTools.dimensionalTrasform;

import generation.curveGeneration.Matrix;
import java.util.ArrayList;

import model.Excel;

class Scalign {
	
	public static ArrayList<Excel> scale(int coeff, ArrayList<Excel> toScale)
	{
		 ArrayList<Excel> result = new  ArrayList<Excel>();
		 
		 double matrix[][] = {   {coeff, 0,0,0},
								 {0, coeff,0,0},
								 {0, 0,coeff,0},
								 {0, 0,  0,  1}
							}; 
		 
		 Matrix scaleMatrix = new Matrix(4,4);
		 scaleMatrix.setMatrix(matrix);
		 
		 for (Excel ex: toScale)
		 {
			 double l[][] = {{ex.getX()}, 
							 {ex.getY()}, 
							 {ex.getZ()}, 
							 {ex.getT()}
					 };
			 Matrix line = new Matrix(4,1);
			 line.setMatrix(l);
			 
			 Matrix multiplied = Matrix.matrixMultiplication(scaleMatrix, line);
			 int x =(int) multiplied.getEl(0, 0);
			 int y =(int) multiplied.getEl(1, 0);
			 int z =(int) multiplied.getEl(2, 0);
			 int t =(int) multiplied.getEl(3, 0);
			 
			 result.add(new Excel(x,y,z,t, ex.getColor()));
		 }
		 
		 return result;
	}
}
