package curveGeneration;

import java.util.ArrayList;

public class Matrix {
	
	private int columns;
	private int rows;
	private double matrix[][];
	public static ArrayList<Double> f = new ArrayList<Double>();
	public static ArrayList<Double> s = new ArrayList<Double>();
	
	
	public Matrix(int n,int m)
	{
		columns = n;
		rows = m;
		matrix = new double[n][m];
	}
	
	public void updateEl(int n,int m,double value)
	{
		matrix[n][m] = value;
	}
	
	public void fillingMatrix(double massive[])
	{
		int index = 0;
		for(int i=0; i<columns; i++)
		{
			for(int j=0; j<rows; j++)
			{
				matrix[i][j] = massive[index];
				index++;
			}
		}
	}
	
	public void showMatrix()
	{
		for(int i=0; i<columns; i++)
		{
			System.out.println();
			for(int j=0; j<rows; j++)
			{
				//System.out.print(matrix[i][j]+" ");
			}
		}
		f.add(matrix[0][0]);
		s.add(matrix[0][1]);
	}
	
	public void showMatrixF()
	{
		System.out.print("First column");

			for(int j=0; j<rows; j++)
			{
				System.out.print(matrix[0][j]);
			}
	}
	
	public void showMatrixS()
	{
		System.out.print("Second column");
			System.out.println();
			for(int j=0; j<rows; j++)
			{
				System.out.print(matrix[1][j]);
			}
	}
	
	public double getEl(int n,int m)
	{
		if(n<columns && m<rows)
		{
			double rez = matrix[n][m];
			return rez;
		}
		
		return 0;
	}
	

	public int getColumns() {
		return columns;
	}

	public void setColumns(int colums) {
		this.columns = colums;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

}
