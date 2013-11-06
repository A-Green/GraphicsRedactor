package transformationTools.dimensionalTrasform;

import generation.curveGeneration.Matrix;

import java.util.ArrayList;

import model.Excel;

public class Rotate {
	
	public static ArrayList<Excel> rotateX(int angle, ArrayList<Excel> toScale)
	{
		 ArrayList<Excel> result = new  ArrayList<Excel>();
		 
		 double matrix[][] = {   {1, 0,0,0},
								 {0, Math.cos(angle * Math.PI/180), Math.sin(angle* Math.PI/180), 0},
								 {0,(-Math.sin(angle* Math.PI/180)), Math.cos(angle* Math.PI/180),0},
								 {0, 0,0,1}
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
	
	public static ArrayList<Excel> rotateY(int angle, ArrayList<Excel> toScale)
	{
		 ArrayList<Excel> result = new  ArrayList<Excel>();
		 
		 double matrix[][] = {   {Math.cos(angle * Math.PI/180), 0, Math.sin(angle* Math.PI/180),0},
								 {0, 1 ,0, 0},
								 {(-Math.sin(angle* Math.PI/180)),0, Math.cos(angle* Math.PI/180),0},
								 {0, 0,0,1}
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
	
	
	public static ArrayList<Excel> rotateZ(int angle, ArrayList<Excel> toScale)
	{
		 ArrayList<Excel> result = new  ArrayList<Excel>();
		 
		 double matrix[][] = {   {Math.cos(angle * Math.PI/180),  Math.sin(angle* Math.PI/180),0,0},								
								 {(-Math.sin(angle* Math.PI/180)),Math.cos(angle* Math.PI/180),0,0},
								 {0, 0 ,1, 0},
								 {0, 0,0,1}
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
