package generation.curveGeneration;

import java.util.ArrayList;

import model.Excel;

public class BesieCurve extends ParametricCurve{

	public BesieCurve(ArrayList<Excel> allEx) 
	{
		super(allEx);
	}
	
	/*
	 * ������� ��� ���������� ��������������
	 * ��������� ����� ��� ������ �� ����� �����
	 */
	public ArrayList<Excel> Calculation()
	{
		System.out.println("besieForm");
		
		//allEx - ������ ��������� �����
		ArrayList<Excel> allEx = new ArrayList<Excel>();
		allEx = getAllexcel();
		
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		for(int i=0;i<allEx.size();i++)
		{
			if(allEx.get(i) == null)
				return null;
		}
		
		if(allEx.size() != 4) 
			return null;
		
		//������� �����
		double masMn[] = {-1,3,-3,1,
				 3,-6,3,0,
				  -3,3,0,0,
				  1,0,0,0};
		setMasMn(masMn);

		/*
		 * ��������� ������� �����
		 * ��� ������ ������ ��������
		 * ���������� �� ����� ��������� �����
		 */
		double masGnx[] = {allEx.get(0).getX(),allEx.get(0).getY(),
							allEx.get(1).getX(),allEx.get(1).getY(),
							allEx.get(2).getX(),allEx.get(2).getY(),
							allEx.get(3).getX(),allEx.get(3).getY(),};
		setMasGnx(masGnx);
		
		double factor = 1.0;
		setFactor(factor);
		
		setDivider(5.0);
	
		//���������� � ��������� ��������� �����
		setForStepExcel(allEx);
		
		/*
		 * ����� ������ ������ ��� ���������� ��������������� ������
		 * � ���������� ���������� ������ ��� � �������� ���������
		 */
		result.addAll(super.Calculation());
		setForStepExcel(null);
		
		return result;
	}
}
