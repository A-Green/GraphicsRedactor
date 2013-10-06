package curveGeneration;

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
