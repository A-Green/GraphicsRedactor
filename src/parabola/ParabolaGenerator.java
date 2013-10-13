package parabola;

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
			int x = ex.getX();
			int y = ex.getY();
			int x0 = x;
			int y0 = y;
			
			int vertX, vertY, diagX, diagY, horX, horY;
			double verticalError, horizontalError, diagonalError;
			
			while(y<size && y>=0) {
			
			if(p>0) {
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
			
			/*verticalError = 2 * p * Math.pow((vertX - x0), 2 ) + y0 - vertY;
			horizontalError = 2 * p * Math.pow((horX - x0), 2 ) + y0 - horY;;
			diagonalError = 2 * p * Math.pow((diagX - x0), 2 ) + y0 - diagY;
			*/
			verticalError = Math.pow((vertX - x0), 2 )/(2*p) + y0 - vertY;
			horizontalError = Math.pow((horX - x0), 2 )/(2*p) + y0 - horY;;
			diagonalError = Math.pow((diagX - x0), 2 )/(2*p) + y0 - diagY;
			
			if(verticalError<0)
				verticalError = verticalError*(-1);
			if(horizontalError<0)
				horizontalError = horizontalError*(-1);
			if(diagonalError<0)
				diagonalError = diagonalError*(-1);
			
			System.out.println("vEr:"+verticalError);
			System.out.println("hEr:"+horizontalError);
			System.out.println("dEr:"+diagonalError);
			
			if(verticalError<horizontalError && verticalError<diagonalError) {
				System.out.println("ChooseVert");
				result.add(new Excel( vertX , vertY, Color.black ));
				result.add(new Excel( (x0+(x0-vertX)) , vertY, Color.black ));
				x = vertX;
				y = vertY;
			}
			
			if(horizontalError<verticalError && horizontalError<diagonalError) {
				System.out.println("ChooseHor");
				result.add(new Excel( horX , horY, Color.black ));
				result.add(new Excel( (x0+(x0-horX)) , horY, Color.black ));
				x = horX;
				y = horY;
			}
			
			if(diagonalError<=verticalError && diagonalError<=horizontalError) {
				System.out.println("ChooseDiag");
				result.add(new Excel( diagX , diagY, Color.black ));
				result.add(new Excel( (x0+(x0-diagX)) , diagY, Color.black ));
				x = diagX;
				y = diagY;
			}

			}
			
			return result;
		}
		

}
