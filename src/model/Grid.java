package model;

public class Grid {
	
	private Excel[][] grid;
	private int size;
	private int step;
	
	public Grid(int n)
	{		
	 grid = new Excel[n][n];
	 setSize(n);
	 setStep(10);
	 
	 for (int i = 0; i < n; i++)
		 for (int j = 0; j < n; j++)
			 grid[i][j] = new Excel();
	}
	
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void setExcel(int x, int y, Excel e)
	{
		grid[x][y]=e;
	}
	
	public Excel getExcel(int x, int y) 
	{
		return grid[x][y]; 
	}
	
	public void setSize(int n)
	{
		size = n;
	}
	
	public int getSize()
	{
		return size;
	}
}
