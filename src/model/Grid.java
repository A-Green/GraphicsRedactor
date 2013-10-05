package model;

public class Grid {
	
	private Excel[][] grid;
	private int size;
	private int step;
	protected ClickedExController clickedEx;
	
	public Grid(int n)
	{		
	 grid = new Excel[n][n];
	 setSize(n);
	 setStep(10);
	 
	 for (int i = 0; i < n; i++)
		 for (int j = 0; j < n; j++)
			 grid[i][j] = new Excel(i,j);
	 
	 clickedEx = new ClickedExController();
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
	
	public Excel getClickedEx()
	{
		return clickedEx.getExcel();
	}
	
	public void removeClickedEx(Excel ex)
	{
		clickedEx.removeExcel(ex);
	}
	
	public void addClickedEx(Excel ex)
	{
		clickedEx.addExcel(ex);
	}
	
	public void clear()
	{
		for (int i = 0; i < this.getSize(); i++)
			for (int j = 0; j < this.getSize(); j++)
				this.getExcel(i, j).setColored(false);
		clickedEx.clear();
	}
}
