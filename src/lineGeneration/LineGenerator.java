package lineGeneration;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import model.Excel;
import model.Grid;

public class LineGenerator {
	
	public static ArrayList<Excel> DDA(Grid g)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		System.out.println("ЦДА");
		int count = 0;
		Point p1 = new Point();
		Point p2 = new Point();
		int mas[] = { 0, 0, 0, 0 };
		int n = 0;
		for (int i = 0; i < g.getSize(); i++) {
			for (int j = 0; j < g.getSize(); j++) {
				if (g.getExcel(i, j).isColored() == true) {
					if (n < 4) {
						mas[n] = i;// *g.getStep();
						n++;
						mas[n] = j;// *g.getStep();
						n++;
					}
					count++;
				}
			}
		}

		if (count != 2) return null;
			else {
			
			p1.x = mas[0]; 
			p1.y = mas[1];
			p2.x = mas[2];
			p2.y = mas[3];
			
			
			int length;
			int modx = (int) (p2.getX() - p1.getX());
			
			if (modx < 0) {
				modx = modx * (-1);
			}
			
			int mody = (int) (p2.getY() - p1.getY());
			
			if (mody < 0) {
				mody = mody * (-1);
			}
			
			if (modx >= mody) {
				length = modx;
			} else {
				length = mody;
			}
			
			if (mody == 0) return horizontalLine(p1.x, p2.x, p1.y);
			if (modx == 0) return verticalalLine(p1.y, p2.y, p1.x);
			if (Math.abs(modx) == Math.abs(mody)) return diagonalLine(p1.x, p1.y, p2.x, p2.y);
			
			double dx = (p2.getX() - p1.getX()) / length;
			double dy = (p2.getY() - p1.getY()) / length;
			
			int signx = Sign(dx);
			int signy = Sign(dy);
			
			double x = p1.getX() + 0.5 * signx;
			double y = p1.getY() + 0.5 * signy;
			
			if(dx>=0 && dy>=0)
			{
				g.getExcel((int) x, (int) y).setColored(true); 
				int i = 0;
				while (i < length) {
				x = x + dx;
				y = y + dy;				
				result.add(new Excel((int) x,(int) y, Color.black));
				i++;
				}
			}
			else
			{
				g.getExcel((int) x, (int) y + 1).setColored(true); 
				int i = 0;
				while (i < length) {
				x = x + dx;
				y = y + dy;				
				result.add(new Excel((int) x,(int) y + 1, Color.black));
				i++;
			}
			}

		}
		
		return result;		
	}
	
	public static ArrayList<Excel> Brezenhem(Excel ex1, Excel ex2)
	{
		System.out.println("brezenhem");
		ArrayList<Excel> result = new ArrayList<Excel>();		

		if (ex1 == null || ex2 == null) return null;

			int x1 = ex1.getX();
			int y1 = ex1.getY();
			int x2 = ex2.getX();
			int y2 = ex2.getY();
			
			int dx = x2 - x1;     //проекция на ось x
			int dy = y2 - y1;     //проекция на ось y
			
			if (dy == 0) return horizontalLine(x1, x2, y1);
			if (dx == 0) return verticalalLine(y1, y2, x1);
			if (Math.abs(dx) == Math.abs(dy)) return diagonalLine(x1, y1, x2, y2);
			
			int incx = Sign(dx);  			
			 /*
	         * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
	         * справа налево по x, то incx будет равен -1.
	         * Это будет использоваться в цикле постороения.
	         */
			int incy = Sign(dy);
			 /*
	         * Аналогично. Если рисуем отрезок снизу вверх -
	         * это будет отрицательный сдвиг для y (иначе - положительный).
	         */
			
			int pdx, pdy,es,el;
			
			dx = Math.abs(dx); //далее мы будем сравнивать: "if (dx < dy)"
			dy = Math.abs(dy); //поэтому необходимо сделать dx = |dx|; dy = |dy|
			
			if(dx > dy)			//определяем наклон отрезка:
			{
				/*
		          * Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
		          * Значит в цикле нужно будет идти по икс (строчка el = dx;), значит "протягивать" прямую по иксу
		          * надо в соответствии с тем, слева направо и справа налево она идёт (pdx = incx;), при этом
		          * по y сдвиг такой отсутствует.
		          */	
				pdx = incx;
				pdy = 0;
				es = dy;
				el = dx;			
			}
			else 	//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
			{
				pdx = 0;
				pdy = incy;
				es = dx;
				el = dy; //тогда в цикле будем двигаться по y
			}
			
			int x = x1;
			int y = y1;			
			int err = el;
			
			for (int i = 0; i < el; i++)
			{
				err -= 2 *es;    //домножаем на 2, чтобы сделать алгоритм целочисленным
				
				if(err < 0)
				{
					err += 2*el; 	//домножаем на 2, чтобы сделать алгоритм целочисленным
					x += incx; 		//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
					y += incy; 		//или сместить влево-вправо, если цикл проходит по y
				}
				else 
				{
					x += pdx;		//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
					y += pdy;		//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
				}

				result.add(new Excel(x,y,Color.black));
			}			
		
		return result;
	}

	public static ArrayList<Excel> WuAlgorithm (Excel ex1, Excel ex2)
	{
		ArrayList<Excel>result = new ArrayList<Excel>();	

		if (ex1 == null || ex2 == null) return null;		
			
			int x1 = ex1.getX();
			int y1 = ex1.getY();
			int x2 = ex2.getX();
			int y2 = ex2.getY();
			
			  double dx = x2 - x1;
			  double dy = y2 - y1;
			  
			  
			  //проверка на простые линии
			  if (dy == 0) return horizontalLine(x1, x2, y1);
			  if (dx == 0) return verticalalLine(y1, y2, x1);
			  if (Math.abs(dx) == Math.abs(dy)) return diagonalLine(x1, y1, x2, y2);
			  		  			  
			  if ( Math.abs(dx) > Math.abs(dy) ) { // если true, значит приращение будет идти по оси x
			    if ( x2 < x1 ) {   			
			      int temp = x1;				// меняем местами координаты чтобы идти слева направо
			      x1 = x2;
			      x2 = temp;
			      
			      temp = y1;
			      y1 = y2;
			      y2 = temp;
			    }
			    double gradient = dy / dx;			// вычисление градиента. будет использоваться для установки цвета пикселя
			    												//далее необходимо обработать первую точку

			    result.add(new Excel(x1,y1, Color.black));
			    												// а теперь вторую
			    double intery = y1 + gradient;

			    result.add(new Excel(x2,y2, Color.black));   
			    
			 // т.к осью приращения была выбрана ось x, то идя по ней от т. x1 до т. х2, используя приращение, выстраивается линия
			 // Исопльзуя значение отклонения пикселя от предполагаемой линии, регулируется интенсивность цвета
			    for(int x = x1 + 1; x <= (x2 - 1); x++) 						    										
			    {			    	
			    	 result.add(new Excel(x,(int) intery, getColor(rfpart(intery))));
			    	 result.add(new Excel(x,(int) intery + 1, getColor(fpart(intery))));
			    	 intery += gradient;
			    }
			  } else {									// дейсвия аналогичны тем, что при верном if, только теперь 
			    if ( y2 < y1 ) {						// главной осью выбирается ось y
			    
			      int temp = x1;
			      x1 = x2;
			      x2 = temp;
			      
			      temp = y1;
			      y1 = y2;
			      y2 = temp;
			    }
			    
			    double gradient = dx / dy;
			    
			    result.add(new Excel(x1,y1, Color.black));
			    
			    double interx = x1 + gradient;

			    result.add(new Excel(x2,y2, Color.black));
			    
			    int y;
			    for(y=y1+1; y <= (y2-1); y++) 
			    {	    	
			    	result.add(new Excel((int) interx, y, getColor(rfpart(interx))));
			    	result.add(new Excel((int) interx + 1, y, getColor(fpart(interx))));
			    	interx += gradient;
			    }
			  }
			
			
	         					
		return result;
		
	}

	private static int Sign(double x)
	{
		if (x < 0) return -1;
		else if (x > 0) return 1;
		return 0;
	}
	
	private static int ipart(double x)
	{
		return (int) x;
	}
	
	private static double fpart(double x)
	{
		return x - ipart(x);
	}
	
	private static double rfpart(double x)
	{
		return 1 - fpart(x);
	}
	
	private static Color getColor(double rate)
	{
		if (rate >= 0.7)
			return Color.black;
		if (rate < 0.7 && rate >= 0.5)
			return Color.black.brighter();
		if (rate < 0.5 && rate >= 0.4)
			return Color.gray;
		if (rate < 0.4 && rate >=0.3)
			return Color.gray.brighter();
		if (rate < 0.3 && rate >= 0.1)
			return Color.lightGray;		
		return null;				
	}
	
	
	public static ArrayList<Excel> horizontalLine(int x1, int x2, int y)
	{	
		System.out.println("horizotal line");
		ArrayList<Excel> result = new ArrayList<Excel>();
		int x = (x1 > x2) ? x2 : x1;
		int length = Math.abs(x1 - x2);
		
		for (int i = 0; i < length; i++)
			result.add(new Excel(x + i, y, Color.black));
		
		return result;
	}
	
	public static ArrayList<Excel> verticalalLine(int y1, int y2, int x)
	{
		System.out.println("vertical line");
		ArrayList<Excel> result = new ArrayList<Excel>();
		int y = (y1 > y2) ? y2 : y1;
		int length = Math.abs(y1 - y2);
		
		for (int i = 0; i < length; i++)
			result.add(new Excel(x, y + i, Color.black));
		
		return result;
	}
	
	public static ArrayList<Excel> diagonalLine(int x1, int y1, int x2, int y2)
	{	
		System.out.println("diagonal line");
		ArrayList<Excel> result = new ArrayList<Excel>();	
		int increm;
		
		if (x1 < x2) 
		{
			increm = (y1 < y2) ? 1 : -1;
			
			for (int x = x1; x < x2; x++, y1+=increm )
			{
				result.add(new Excel(x, y1, Color.black));
			}

		}
		else 
		{
			increm = (y1 < y2) ? -1 : 1;
			
			for (int x = x2; x < x1; x++, y2+=increm )
			{
				result.add(new Excel(x, y2, Color.black));
			}
		}
				
		return result;		
	}
	
}

	