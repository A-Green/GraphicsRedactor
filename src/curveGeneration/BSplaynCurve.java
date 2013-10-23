package curveGeneration;

import java.util.ArrayList;

import model.Excel;

public class BSplaynCurve extends ParametricCurve{

	public BSplaynCurve(ArrayList<Excel> allEx) {
		super(null,null,null,null,allEx);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Excel> Calculation()
	{

		System.out.println("BSplaynForm");
		
		ArrayList<Excel> allEx = new ArrayList<Excel>();
		allEx = getAllexcel();
		
		if(allEx.size()<4)
			return null;

		ArrayList<Excel> result = new ArrayList<Excel>();

		
		double masMn[] = {-1,3,-3,1,
				  3,-6,3,0,
				  -3,0,3,0,
				  1,4,1,0};
		setMasMn(masMn);
		
		double factor = 1/6.0;
		setFactor(factor);
		
		for(int i=1;i<allEx.size()-2;i++)
		{			
			ArrayList<Excel> masForStep = new ArrayList<Excel>();
			masForStep.add(allEx.get(i-1));
			masForStep.add(allEx.get(i));
			masForStep.add(allEx.get(i+1));
			masForStep.add(allEx.get(i+2));
			
			double masGnx[] = {allEx.get(i-1).getX(),allEx.get(i-1).getY(),
							   allEx.get(i).getX(),allEx.get(i).getY(),
							   allEx.get(i+1).getX(),allEx.get(i+1).getY(),
							   allEx.get(i+2).getX(),allEx.get(i+2).getY()};
			
			setForStepExcel(masForStep);
			setMasGnx(masGnx);
			result.addAll(super.Calculation());
			setForStepExcel(null);
		}
		
		return result;
	}
	
	public int getIndex(Excel masEx[],int index)
	{
		int size = masEx.length;
		
		if (index == -1)
			return size - 1;
		else if (index == size)
			return 0;
		else if (index > size)
			return index - size;
		
		return index;
	}

}
