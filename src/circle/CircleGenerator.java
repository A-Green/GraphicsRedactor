package circle;

import java.awt.Point;
import java.util.ArrayList;

import model.Grid;

public class CircleGenerator {

	public static ArrayList<Point> circle(Grid g, int R)
	{
		ArrayList<Point> result = new ArrayList<Point>();
		int dx = -1;
		int dy = -1;
		for (int i = 0; i < g.getSize(); i++) 
		{
			for (int j = 0; j < g.getSize(); j++)
			{
				if (g.getExcel(i, j).isColored())
				{
					dx = i;
					dy = j;
					break;
				}
			}
			if (dx != -1) break;
		}
		
		if (dx == -1) return null;
		
		g.getExcel(dx, dy).setColored(false);
		

		int limit = 0;
		int x = 0;
		int y = R;
		double delta = 2 - 2 * R;       //первоначальное значение ошибки
		
		if (R > 0)
		{
		result.add(new Point(x + dx,dy + y));
		result.add(new Point(x + dx,dy - y));
		}
		
		while(y >= limit)
		{
			if (delta > 0)							// диагональная точка лежит вне окружности. нужно выбрать между вертикальным и диагональным пикселем
			{
				double sigma = 2*delta - 2*x - 1; // характеризует расстояние от окружности до пикселя
				
				if (sigma <= 0)						// если верно, значит, расстояние от ок-ти до диагонального пикселя меньше или равно, 
				{									// выбираем диагональный 
					x += 1;
					y -= 1;
					delta = delta + 2*x - 2*y + 2;					
				}
				else							// верно обратное, выбираем вертикальный пиксель
				{
					y -= 1;
					delta = delta - 2*y + 1;
				}
			}
			
			else if (delta == 0)				// случай, когда диагональный пиксель лежит точно на окружности, выбираем его
			{
				x += 1;
				y -= 1;
				delta = delta + 2*x - 2*y + 2;	
			}
			else 								  // случай, когда диагональная точка лежит внутри окружности. 
			{									  // Нужно выбрать между горизонтальным и диагональным пикселем
				double sigma = 2*delta + 2*y - 1; // Характеризует расстояние от коружности до ближайшего пикселя
				
				if (sigma > 0)						// Расстояние до горизонтального пикслея больше, берём диагональный
				{
					x += 1;
					y -= 1;
					delta = delta + 2*x - 2*y + 2;
				}
				else								// иначе берём горизонтальный пиксель
				{
					x +=1;
					delta = delta + 2*x + 1;
				}
			}
			
			result.add(new Point(dx + x,dy + y)); // смещаем точки относительно центра
			result.add(new Point(dx + x,dy - y));
			result.add(new Point(dx - x,dy + y));
			result.add(new Point(dx - x,dy - y));
			
		}
	
		return result;		
	}
}
