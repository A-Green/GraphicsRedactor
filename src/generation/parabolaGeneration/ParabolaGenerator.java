package generation.parabolaGeneration;

import java.awt.Color;
import java.util.ArrayList;

import model.Excel;

public class ParabolaGenerator {
	//Парабола
		public static ArrayList<Excel> parabola(Excel ex, double p, int size) {
			//System.out.println("it's p:"+p);
			ArrayList<Excel> result = new ArrayList<Excel>();
			if(ex == null)
				return null;
			int x = ex.getX(); //смещение центра начала координат по оси Х
			int y = ex.getY(); // смещение центра начала координат по оси Y
			int x0 = x;
			int y0 = y;
			
			//координаты пикселей-претендентов
			int vertX, vertY, diagX, diagY, horX, horY;
			// значения ошибки 
			double verticalError, horizontalError, diagonalError;
			
			while(y<size && y>=0) {

			result.add(new Excel(ex.getX(), ex.getY(), Color.black));
			
			while(y<size && y>0) {
			
				//System.out.print((x ) + "   " + (y ) +  "   "); 
			if(p>0) { // корректировка смещения пикселей-претендентов в зависимости 
				// от того, вверх или вниз направлены ветви параболы
			vertX = x;
			vertY = y + 1;
			diagX = x + 1;
			diagY = y + 1;
			horX = x + 1;
			horY = y;
			}
			else {
			vertX = x;
			vertY = y - 1;
			diagX = x + 1;
			diagY = y - 1;
			horX = x + 1;
			horY = y;
			}
			// вычисление значений всех трёх ошибок
			verticalError = Math.pow((vertX - x0), 2 )/(2*p) + y0 - vertY;
			//System.out.print(verticalError + "  "); 
			horizontalError = Math.pow((horX - x0), 2 )/(2*p) + y0 - horY;
			//System.out.print(horizontalError + "  "); 
			diagonalError = Math.pow((diagX - x0), 2 )/(2*p) + y0 - diagY;
			//System.out.print(diagonalError + "  "); 
			//корректировка ошибок
			if(verticalError<0)
				verticalError = verticalError*(-1);
			if(horizontalError<0)
				horizontalError = horizontalError*(-1);
			if(diagonalError<0)
				diagonalError = diagonalError*(-1);
			// если значения ошибки вертикального пикселя является наименьшим,
			//то выбирается ветртикальный пиксель и считается текущим
			//Далее аналогично с диагональным и горизонтальным пикселем
			if(verticalError<horizontalError && verticalError<diagonalError) {
				result.add(new Excel( vertX , vertY, Color.black ));
				result.add(new Excel( (x0+(x0-vertX)) , vertY, Color.black ));
				x = vertX;
				y = vertY;
			}
			
			if(horizontalError<verticalError && horizontalError<diagonalError) {
				result.add(new Excel( horX , horY, Color.black ));
				result.add(new Excel( (x0+(x0-horX)) , horY, Color.black ));
				x = horX;
				y = horY;
			}
			
			if(diagonalError<=verticalError && diagonalError<=horizontalError) {
				result.add(new Excel( diagX , diagY, Color.black ));
				result.add(new Excel( (x0+(x0-diagX)) , diagY, Color.black ));
				x = diagX;
				y = diagY;
			}

			}
			return result;
		}
			return null;
		

}
}
