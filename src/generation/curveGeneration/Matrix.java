package generation.curveGeneration;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Matrix {
	
	private int columns;
	private int rows;
	private double matrix[][];
	
	
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
				System.out.print(matrix[i][j]+" ");
			}
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
	
	public static Matrix matrixMultiplication(Matrix A, Matrix B)
	{
		Matrix C = new Matrix(A.getColumns(),B.getRows());
		
		if(A.getRows() == B.getColumns())
		{
			double rezultValue = 0;
	
			for(int i=0; i<A.getColumns(); i++)
			{
				for(int j=0; j<B.getRows(); j++)
				{
					for(int p=0; p<A.getRows(); p++)
					{
						rezultValue+=A.getEl(i, p)*B.getEl(p, j);
					}
					C.updateEl(i, j, new BigDecimal(rezultValue).setScale(3, RoundingMode.UP).doubleValue());
					rezultValue = 0;
				}
			}
			return C;
		}
		else
		{
			System.out.println("Количество столбцов 1ой матрицы должна быть равно" +
					" количеству строк 2ой !!!");
		}
		
		return null;
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
