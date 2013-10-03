package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import model.*;
import javax.swing.JPanel;

public class GridView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grid grid;
	//Массив ячеек, которые будут отрисовываться пошагово
	private ArrayList<Excel> steplyArray = null;
	
	public GridView(Grid g)
	{
		grid = g;
		setOpaque(false);
		setBackground(Color.WHITE);
	}
		
	  protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setColor(Color.white);
	        Color defColor = g2d.getColor(); //цвет по умолчанию
	        
	        Dimension d = new Dimension();			//Вот он, вот он, скролл!!
	        d.height=grid.getStep()*grid.getSize();	//Скроооолл!!
	        d.width=grid.getStep()*grid.getSize();	//Здоровенный скролл!!
	        this.setPreferredSize(d);				//СКРОООЛЛ!!!
	        this.revalidate();
	                
	        int step = grid.getStep();

	        for (int i = 0; i < grid.getSize(); i++)
	        {	        	
	        	int y[] = {i*step, i*step, (i+1)*step, (i+1)*step};
	        	for(int j = 0; j< grid.getSize(); j++)
	        	{
	        int x[] = {j*step, (j+1)*step, (j+1)*step, j*step};	       

	        Polygon p = new Polygon(x,y,4);  
	                
	        if (grid.getExcel(j, i).isColored()==true)
	        {
		        g2d.setColor(grid.getExcel(j, i).getColor());
		        g2d.fillPolygon(p);
		        g2d.setColor(defColor);
	        }
	        else
	        	g2d.drawPolygon(p);

	        	}
	        }    
	    }
	  
	  public void drawDots(ArrayList<Excel> exs)
	  {
		  
		  for (int i = 0; i < exs.size(); i++)
		  {
			  int x = exs.get(i).getX();
			  int y = exs.get(i).getY();
			  
			  if (x < grid.getSize() && y < grid.getSize() && x >= 0 && y >= 0) 
			  {
				  grid.setExcel(x, y, exs.get(i));
			  }
		  }
	  }
	  
	  public void setSteplyArray(ArrayList<Excel> arr)
	  {
		  steplyArray = arr;
	  }
	  
	  public void drawDotSteply()
	  {
		  if (steplyArray != null && !steplyArray.isEmpty())
		  {
			 Excel ex = steplyArray.get(0);
			 grid.setExcel(ex.getX(), ex.getY(), ex);
			 steplyArray.remove(0);
		  }
	  }
}
