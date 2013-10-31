package generation.curveGeneration;

import java.util.ArrayList;

import model.Excel;

public class BSplaynCurve extends ParametricCurve{

	public BSplaynCurve(ArrayList<Excel> allEx) {
		super(allEx);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Функция для вычисления резулитирощего
	 * множества точек для кривой методом В-сплайна
	 */
	public ArrayList<Excel> Calculation()
	{

		System.out.println("BSplaynForm");
		
		//allEx - массив граничных точек
		ArrayList<Excel> allEx = new ArrayList<Excel>();
		allEx = getAllexcel();
		
		for(int i=0;i<allEx.size();i++)
		{
			if(allEx.get(i) == null)
				return null;
		}
		
		if(allEx.size()<4)
			return null;

		ArrayList<Excel> result = new ArrayList<Excel>();

		//матрица В-сплайна
		double masMn[] = {-1,3,-3,1,
				  3,-6,3,0,
				  -3,0,3,0,
				  1,4,1,0};
		setMasMn(masMn);
		
		double factor = 1/6.0;
		setFactor(factor);
		setDivider(1.0);
		
		/*
		 * Построения кривой по методу В-сплайна
		 * происходит с помощью расширения множество заданных вершин
		 * соседними точками для первого и последнего сегмента сплайна
		 */
		for(int i=1;i<allEx.size()-2;i++)
		{			
			ArrayList<Excel> masForStep = new ArrayList<Excel>();
			masForStep.add(allEx.get(i-1));
			masForStep.add(allEx.get(i));
			masForStep.add(allEx.get(i+1));
			masForStep.add(allEx.get(i+2));
			
			/*
			 * Генерация вектора B-сплайна
			 * 1<=i<=n-1, 0<=t<1, где t - параметр, изменяющийся от 0 до 1
			 * i-счетчик сегментов кривой 
			 * n-количестов вершин
			 * (n-1) - количество сегментов сплайна
			 */
			double masGnx[] = {allEx.get(i-1).getX(),allEx.get(i-1).getY(),
							   allEx.get(i).getX(),allEx.get(i).getY(),
							   allEx.get(i+1).getX(),allEx.get(i+1).getY(),
							   allEx.get(i+2).getX(),allEx.get(i+2).getY()};
			
			//добавление в результат граничных точек
			setForStepExcel(masForStep);
			
			setMasGnx(masGnx);
			
			/*
			 * вызов общего метода для построения параметрических кривых
			 * и добавление результата работы его в конечный результат
			 */
			result.addAll(super.Calculation());
			setForStepExcel(null);
		}
		
		return result;
	}

}
