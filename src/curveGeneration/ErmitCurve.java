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
	 * ������� ��� ���������� ��������������
	 * ��������� ����� ��� ������ �� ����� �����
	 */
	public ArrayList<Excel> Calculation()
	{

		System.out.println("ermitForm");

		//allEx - ������ ��������� �����
		ArrayList<Excel> allEx = new ArrayList<Excel>();
		allEx = getAllexcel();
		
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		if(allEx.size() != 4) 
			return null;

		//������� ������
		double masMn[] = {2,-2,1,1,
				 -3,3,-2,-1,
				  0,0,1,0,
				  1,0,0,0};
		setMasMn(masMn);
		
		/*
		 * ���������� �������� ����������� ��� ���� ��������� �����
		 */
		Point r1 = rVector(allEx.get(0), allEx.get(1));
        Point r4 = rVector(allEx.get(2), allEx.get(3));
        
        /*
         * ��������� ������� ������
         * ������ ������ �������� ������� �� ��������� �������� �����
         * ������ �� ��������� ������� �����
         * � 3�� � 4�� �� �������� ����������� ���� �����
         */
		double masGnx[] = {allEx.get(0).getX(),allEx.get(0).getY(),
							allEx.get(3).getX(),allEx.get(3).getY(),
							r1.getX(),r1.getY(),r4.getX(),r4.getY()};
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
