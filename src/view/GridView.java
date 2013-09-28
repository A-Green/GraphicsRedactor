package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import model.*;
import javax.swing.JPanel;

public class GridView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Grid grid;
	
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
	        //g2d.setClip(0,0, getWidth(), getHeight() * 2);
	        //g2d.setClip(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
	        /*  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setColor(Color.blue);
	        g2d.fillOval(10, 10, getWidth() - 20, getHeight() * 2 - 20);
	        g2d.setColor(Color.red);
	        g2d.fillOval(20, 20, getWidth() - 40, getHeight() - 40);
	        g2d.setColor(Color.yellow);
	        g2d.fillOval(30, 30, getWidth() - 60, getHeight() - 60);
	        g2d.setColor(Color.black);
	        g2d.fillOval(getWidth()/4 - getWidth()/16, getHeight()/2-getHeight()/8, getWidth()/8, getHeight()/8);
	        g2d.fillOval(getWidth()*3/4 - getWidth()/16, getHeight()/2-getHeight()/8, getWidth()/8, getHeight()/8);
	        g2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        g2d.drawArc(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2, 225, 90);
	        */
	        
	      //  g2d.drawLine(0, 100, 100, 100);
	        
	        Dimension d = new Dimension();			//Вот он, вот он, скролл!!
	        d.height=grid.getStep()*grid.getSize();	//Скроооолл!!
	        d.width=grid.getStep()*grid.getSize();	//Здоровенный скролл!!
	        this.setPreferredSize(d);				//СКРОООЛЛ!!!
	        this.revalidate();
	        
	        
	        int step = grid.getStep();
	       // System.out.println(grid.getStep());
	        for (int i = 0; i < grid.getSize(); i++)
	        {	        	
	        	int y[] = {i*step, i*step, (i+1)*step, (i+1)*step};
	        	for(int j = 0; j< grid.getSize(); j++)
	        	{
	        int x[] = {j*step, (j+1)*step, (j+1)*step, j*step};	       
	        int n = 4;
	        Polygon p = new Polygon(x,y,n);  
	                
	        if (grid.getExcel(j, i).isColored()==true)
	        {
	        g2d.setColor(grid.getExcel(j, i).getColor());
	        g2d.fillPolygon(p);
	        g2d.setColor(defColor);
	        }
	        else
	        g2d.drawPolygon(p);
	       //

	        	}}
	    
	    }
	

}
