package curveGeneration;

import java.awt.Point;
import java.util.ArrayList;

import model.Excel;

public class ErmitCurve extends ParametricCurve
{
	public ErmitCurve(ArrayList<Excel> allEx) 
	{
		super(allEx);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Функция для вычисления резулитирощего
	 * множества точек для кривой по форме Безье
	 */
	public ArrayList<Excel> Calculation()
	{

		System.out.println("ermitForm");

		//allEx - массив граничных точек
		ArrayList<Excel> allEx = new ArrayList<Excel>();
		allEx = getAllexcel();
		
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		if(allEx.size() != 4) 
			return null;

		//матрица Эрмита
		double masMn[] = {2,-2,1,1,
				 -3,3,-2,-1,
				  0,0,1,0,
				  1,0,0,0};
		setMasMn(masMn);
		
		/*
		 * Вычисление векторов касательных для пары граничных точек
		 */
		Point r1 = rVector(allEx.get(0), allEx.get(1));
        Point r4 = rVector(allEx.get(2), allEx.get(3));
        
        /*
         * Генерация вектора Эрмита
         * первая строка которого состоит из координат начально точки
         * вторая из координат конечно точки
         * а 3ья и 4ая из векторов касательных этих точек
         */
		double masGnx[] = {allEx.get(0).getX(),allEx.get(0).getY(),
							allEx.get(3).getX(),allEx.get(3).getY(),
							r1.getX(),r1.getY(),r4.getX(),r4.getY()};
		setMasGnx(masGnx);
		
		double factor = 1.0;
		setFactor(factor);
		
		setDivider(5.0);
		
		//добавление в результат граничных точек
		setForStepExcel(allEx);
		
		/*
		 * вызов общего метода для построения параметрических кривых 
		 * и добавление результата работы его в конечный результат
		 */
		result.addAll(super.Calculation());
		setForStepExcel(null);
		
		return result;
	}
}
