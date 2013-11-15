package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import model.*;
import javax.swing.JPanel;

import shape.Shape;
import shape.dimensional.DimensionalObject;

public class GridView extends JPanel{

	private static final long serialVersionUID = 1L;
	//ћассив €чеек, которые будут отрисовыватьс€ пошагово
	private ArrayList<Excel> steplyArray;
	//ћассив закрашенных €чеек
	private ArrayList<Excel> coloredEx;
	// онтроллер кликнутых €чеек
	private ClickedExController ClickedController;
	//Ўаг сетки
	private int step;	
	//Ўирина экрана
	double w;
	//¬ысота экрана
	double h;
	//ћассив фигур
	private ArrayList<Shape> figures;
	
	private Shape selectedFigure;

	public GridView()
	{
		step = 1;
		setOpaque(false);
		setBackground(Color.WHITE);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = new Dimension();
		d = t.getScreenSize();
		w = d.getWidth();
	    h = d.getWidth();
	    coloredEx = new ArrayList<Excel>();
	    steplyArray = new ArrayList<Excel>();
	    ClickedController = new ClickedExController();
	    figures = new ArrayList<Shape>();
	    selectedFigure = null;
	}
		
	  protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setColor(Color.white);
	        
	        Dimension d = new Dimension();
	        d.height= (int) (h * step);	
	        d.width= (int) (w* step);	
	        this.setPreferredSize(d);				
	        this.revalidate();
	      //¬ертикальные линии	        
	        int i = 0;
	        int x1 = 0;
	        int y1 = 0;
	        int x2 = 0;
	        int y2 = d.height;
	        	
	        while(i < w)
	        {
	        	g2d.drawLine(x1, y1, x2, y2);
	        	x1 += step;
	        	x2 +=step;
	        	i++;
	        	
	        }
	        //√оризонтальные линии
	         x1 = 0;
	         y1 = 0;
	         x2 = d.width;
	         y2 = 0;
	         i = 0;
	         while(i < h)
	         {
		        	g2d.drawLine(x1, y1, x2, y2);
		        	y1 += step;
		        	y2 +=step;
		        	i++;
	         }
	         //ќтрисовка кликнутых €чеек
	         for(Excel ex: ClickedController.getClickedExes())
	         {
	        	 if (ex.isColored())
	        	 {    		 
	        		 int x[] = {ex.getX() * step + step,ex.getX() * step ,ex.getX() * step,ex.getX()* step + step};
	        		 int y[] = {ex.getY()* step,ex.getY()* step,ex.getY()* step + step,ex.getY()* step + step};
	        		 g2d.setColor(ex.getColor());
	        		 g2d.fillPolygon(x,y,4);
	        	 }
	         }
	         //ќтрисовка дополнительных закрашенных €чеек (дл€ пошагового)
	         for(Excel ex: coloredEx)
	         {
	        	 if (ex.isColored())
	        	 {    		 
	        		 int x[] = {ex.getX() * step + step,ex.getX() * step ,ex.getX() * step,ex.getX()* step + step};
	        		 int y[] = {ex.getY()* step,ex.getY()* step,ex.getY()* step + step,ex.getY()* step + step};
	        		 g2d.setColor(ex.getColor());
	        		 g2d.fillPolygon(x,y,4);
	        	 }
	         }
	        //ќтрисовка фигур 
	         for (Shape figure: figures)
	         {
	        	 ArrayList<Excel>colored = figure.getColoredExes();
	        	 
	        	 for(Excel ex: colored)
		         {
		        	 if (ex.isColored())
		        	 {    		 
		        		 int x[] = {ex.getX()/ex.getZ() * step + step,ex.getX()/ex.getZ() * step ,ex.getX()/ex.getZ() * step,ex.getX()/ex.getZ()* step + step};
		        		 int y[] = {ex.getY()/ex.getZ()* step,ex.getY()/ex.getZ()* step,ex.getY()/ex.getZ()* step + step,ex.getY()/ex.getZ()* step + step};
		        		// int x[] = {ex.getX() * step + step,ex.getX() * step ,ex.getX() * step,ex.getX()* step + step};
			        	// int y[] = {ex.getY()* step,ex.getY()* step,ex.getY()* step + step,ex.getY()* step + step};
			        		 
		        		 g2d.setColor(ex.getColor());
		        		 g2d.fillPolygon(x,y,4);
		        	 }
		         }
	         }
	         
	         if (selectedFigure != null)
	         for (Excel ex: selectedFigure.getColoredExes())
	         {
	         int x[] = {ex.getX() * step + step,ex.getX() * step ,ex.getX() * step,ex.getX()* step + step};
    		 int y[] = {ex.getY()* step,ex.getY()* step,ex.getY()* step + step,ex.getY()* step + step};
    		 g2d.setColor(Color.GREEN.darker());
    		 g2d.fillPolygon(x,y,4);
	         }
	
	    }

	  //«аполн€ет массив пошаговой отрисовки
	  public void setSteplyArray(Shape line)
	  {
		  ArrayList<Excel> arr = line.getColoredExes();
		  for (Excel ex: arr)
		  {
			  if (ex.getX() < 0 && ex.getY() < 0)
				  arr.remove(ex);
		  }
		  steplyArray = arr;
	  }
	  //ќчищает массив пошаговой отрисовки
	  public void clearStepArray()
	  {
		  steplyArray.clear();
	  }
	  
	  // рисует первую точку из массива дл€ пошаговой отрисовки
	  public void drawDotSteply()
	  {
		  if (!steplyArray.isEmpty())
		  {
			 Excel ex = steplyArray.get(0);
			 coloredEx.add(ex);
			 steplyArray.remove(0);
		  }
	  }
	  //«адать шаг сетки
	  public void setStep(int s)
	  {
		  step = s;
		  if (s < 1)
			  step = 1;
	  }
	  //ѕолучить шаг
	  public int getStep()
	  {
		  return step;
	  }
	  //возвращает true, если €чейка уже есть в массиве закрашенных
	  public boolean contains(Excel ex)
	  {
		  for (Excel e: coloredEx)
		  {
			  if (e.getX() == ex.getX() && e.getY() == ex.getY())
				  return true;
		  }
		  
		  for (Excel e: ClickedController.getClickedExes())
		  {
			  if (e.getX() == ex.getX() && e.getY() == ex.getY())
				  return true;
		  }
		  
		  if (selectedFigure != null)
			  for(Excel e: selectedFigure.getColoredExes())
			  {
				  if (e.getX() == ex.getX() && e.getY() == ex.getY())
					  return true;
			  }
		  return false;
	  }
	  //удал€ет €чейку из массива закрашенных €чеек, а также из контроллера кликнутых
	  public void removeEx(Excel ex)
	  {
		  for(Excel e: coloredEx)
		  {
			  if (ex.getX() == e.getX() && ex.getY() == e.getY())
				  {
				  coloredEx.remove(e);
				  break;
				  }
		  }
		  
		  ClickedController.removeExcel(ex);
	  }
	  //добавл€ет
	  public void addEx(Excel ex)
	  {
		  if (ex.getX() >= 0 && ex.getY() >= 0)
		  {
			  ClickedController.addExcel(ex);
		  }
		  		 
	  }
	  
	  public void clear()
	  {
		  coloredEx.clear();
		  steplyArray.clear();
		  ClickedController.clear();
		  figures.clear();
		  selectedFigure = null;
	  } 
	  
	  public Excel getClickedEx()
	  {
		 return ClickedController.getExcel();
	  }
	  
	  public int getH()
	  {
		  return (int) h;
	  }
	  public int getW()
	  {
		  return (int) w;
	  }
	  
	  public void moveLine(int x1, int y1, int x2, int y2)
	  {
		  Excel begin = new Excel(x1,y1);
		  Excel end = new Excel(x2, y2);
		  
		  for(Shape figure: figures)
		  {
			  figure.move(begin, end);
		  }
	  }
	  
	  public void rotate(int angle)
	  {
		
		  if(selectedFigure != null)
			  selectedFigure.rotate(angle);
		  
	  }
	  
	  public void addFugure(Shape figure)
	  {
		  figures.add(figure);
	  }
	  //возвращает все кликнутые €чейки
	  public ArrayList<Excel> getAllClicked()
	  {
		  return ClickedController.getClickedExes();
	  }
	  
	  public ArrayList<Excel> popAllClicked()
	  {
		  return ClickedController.popAll();
	  }
	  
	  public void setSelectedFigure(Shape fig)
	  {
		  selectedFigure = fig;
	  }
	  
	  public Shape getSelectedFigure(){
	  return selectedFigure;
	  }
	  
	  public void rotateX(int angle){
		  //”””””””””∆∆∆јјј——————Ќќ!!!переделать!!! 
		  int a;
		/*  for (Shape figure: figures)
		  {
			 if(figure.getClass().toString().contains("dimensional")){
				 ((DimensionalObject) figure).rotateX(angle);
			 }
		  }
		  */
		  if (selectedFigure != null)
		  if(selectedFigure.getClass().toString().contains("dimensional")){
				 ((DimensionalObject) selectedFigure).rotateX(angle);
			 }
		  
	  }
	  
	  public void rotateY(int angle){
		  //”””””””””∆∆∆јјј——————Ќќ!!!переделать!!! 
		  int a;
		  /*for (Shape figure: figures)
		  {
			 if(figure.getClass().toString().contains("dimensional")){
				 ((DimensionalObject) figure).rotateY(angle);
			 }
		  }*/
		  if (selectedFigure != null)
		  if(selectedFigure.getClass().toString().contains("dimensional")){
				 ((DimensionalObject) selectedFigure).rotateY(angle);
			 }
	  }
	  
	  public void rotateZ(int angle){
		  //”””””””””∆∆∆јјј——————Ќќ!!!переделать!!! 
		  int a;
		 /* for (Shape figure: figures)
		  {
			 if(figure.getClass().toString().contains("dimensional")){
				 ((DimensionalObject) figure).rotateZ(angle);
			 }
		  }*/
		  if (selectedFigure != null)
		  if(selectedFigure.getClass().toString().contains("dimensional")){
				 ((DimensionalObject) selectedFigure).rotateZ(angle);
			 }
	  }
	  
	  public boolean select(Excel ex)
	  {
		  for (Shape figure: figures)
		  {
			  ArrayList<Excel> temp = figure.getColoredExes();
			  
			  for (Excel colEx: temp)
			  {
				  if (colEx.equals(ex))
				  {
					  System.out.println(figure.getClass().toString());
					  if (selectedFigure != null)
						  figures.add(selectedFigure);
					  
					  selectedFigure = figure;				  
					  figures.remove(figure);
					  return true;
				  }
			  }
		  }
		  
		  return false;
	  }
	  

}
