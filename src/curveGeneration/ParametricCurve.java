package curveGeneration;

import java.awt.Color;
import java.awt.Point;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Excel;

public class ParametricCurve {
	
	public ParametricCurve(ArrayList<Excel> allExcel)
	{
		allexcel = allExcel;
	}
	
	
	/*
	 * ����� ������� ��� ���������� ������, ��������� ����� 
	 * �����, ������, �-�������
	 */
	public ArrayList<Excel> Calculation()
	{	
		NumberFormat formatter = new DecimalFormat("0.0##########");
		
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		
		for(int i=0;i<forStepExcel.size();i++)
		{
			/*
			 * forStepExcel - ������, � ������ �������� ��� ��������� �����,
			 * ������� ������������ � ������� ����
			 */
			forStepExcel.get(i).setColor(Color.red);
		}
		/*else
		{
			ex1.setColor(Color.red);
			ex2.setColor(Color.red);
			ex3.setColor(Color.red);
			ex4.setColor(Color.red);
		}*/
			
			Matrix Gnx = new Matrix(4,2); // Gnx - ������ ������, ����� ��� B-�������
			Gnx.fillingMatrix(masGnx);
			
			Matrix Mn = new Matrix(4,4); // Mn - ������� ������, ����� ��� B-�������
			Mn.fillingMatrix(masMn);
			
			/*
			 *Cx = Mn * Gnx, �� ���� � ������� Cx ������������
			 *��������� ������������ ������� Mn � Gnx
			*/
			Matrix Cx; 
			
			if(matrixMultiplication(Mn,Gnx) != null)
			{
				Cx = matrixMultiplication(Mn,Gnx); // ��������� ������������ Gnx � Mn
				
				double step = 0.0;
				
				// ����������� ���� ��������� ��������� t[0,1] � ������� ������� getStep()
				step = getStep(forStepExcel.get(0),forStepExcel.get(1),forStepExcel.get(2),forStepExcel.get(3)); 

				for(double i=0; i<=1; i+=step)
				{
					// T - [t^3 t^2 t^1 1] , ��� t - ��� ��������, ������� ���������� � ����� step �� 0 �� 1
					Matrix T = new Matrix(1,4);
					double masT[] = {factor*Double.parseDouble(formatter.format(Math.pow(i,3)).replace(',', '.')),
							factor*Double.parseDouble(formatter.format(Math.pow(i,2)).replace(',', '.')),
							factor*Double.parseDouble(formatter.format(Math.pow(i,1)).replace(',', '.')),
							factor*1.0};
					T.fillingMatrix(masT);
					
					if(matrixMultiplication(T,Cx) != null)
					{
						Matrix Pt;
						/*
						 * Pt - �������� ��������� ���������� ��������, ������������ ������� ������
						 * ����������� ��� ������������� ������ T �� Cx
						 */
						Pt = matrixMultiplication(T,Cx);
						//Pt.showMatrix();
						

						
						if(Pt.getColumns() == 1 && Pt.getRows() == 2)
						{
							// ���������� � ������ �����, ���������� � ���������� ���������� ��������
							result.add(new Excel((int)Pt.getEl(0, 0),(int)Pt.getEl(0, 1),Color.black));
						}
					}
				}
				
				// ���������� ��������� ����� � �������������� ������
				result.addAll(forStepExcel); 
			}
		
		return result;
	}
	
	/*
	 * �������, ���������������� ��� ����������
	 * ���������� ������������ ����� ���� ������
	 */
	public static Matrix matrixMultiplication(Matrix A, Matrix B)
	{
		Matrix C = new Matrix(A.getColumns(),B.getRows());
		
		if(A.getRows() == B.getColumns())
		{
			double rezultValue = 0;
	
			for(int i=0; i<A.getColumns(); i++)
			{
				for(int j=0; j<B.getRows(); j++)
				{
					for(int p=0; p<A.getRows(); p++)
					{
						rezultValue+=A.getEl(i, p)*B.getEl(p, j);
					}
					C.updateEl(i, j, new BigDecimal(rezultValue).setScale(3, RoundingMode.UP).doubleValue());
					rezultValue = 0;
				}
			}
			return C;
		}
		else
		{
			System.out.println("���������� �������� 1�� ������� ������ ���� �����" +
					" ���������� ����� 2�� !!!");
		}
		
		return null;
	}
	
	/*
	 * ������� ��� ���������� ���� 
	 * ��� ��������� t[0,1]
	 */
	public static double getStep(Excel e1,Excel e2,Excel e3,Excel e4)
	{
		double step = 0;
		
		double max_size = 0;
		
		List<Excel> list = new LinkedList<Excel>();
		list.add(e1);
		list.add(e2);
		list.add(e3);
		list.add(e4);
		
		for(int i=0;i<list.size();i++)
		{
			/*
			 * ����� ������������� ���������� ����� �����
			 * ������ ���������� �������
			 */
			if(i + 1 != list.size())
			{
				Excel point1 = list.get(i);
				Excel point2 = list.get(i+1);
				double current_max = Math.max(Math.abs((double)point2.getX()-(double)point1.getX() ), Math.abs( (double)point2.getY()-(double)point1.getY()));
				
				if(current_max > max_size)
				{
					max_size = current_max;
				}
			}
		}
		
		/*
		 * ���������� ���� ����� ������� 
		 * 1 �� ������������ ���������� ����� �������.
		 * ����� ��� ��� ��� �� 3, ��� ���������� ������ ����,
		 * ��� ����, ����� �� ���� �������� ��� ���������� 
		 * ������.
		 */
		step = (1.0/max_size)/getDivider();
		
		return step;
	}
	
	/*
	 * ������� ��� ���������� ��������� 
	 * ������� �����������, ������ ��� 
	 * ����� ������
	 */
	public static Point rVector(Excel Ex1,Excel Ex2)
	{
		 return new Point(Ex2.getX() - Ex1.getX(), Ex2.getY() - Ex1.getY());	
	}

	public static void setMasGnx(double[] masGnx) {
		ParametricCurve.masGnx = masGnx;
	}

	public static void setMasMn(double[] masMn) {
		ParametricCurve.masMn = masMn;
	}

	public static void setFactor(double factor) {
		ParametricCurve.factor = factor;
	}

	public static ArrayList<Excel> getAllexcel() {
		return allexcel;
	}

	public static void setAllexcel(ArrayList<Excel> allexcel) {
		ParametricCurve.allexcel = allexcel;
	}

	public static ArrayList<Excel> getForStepExcel() {
		return forStepExcel;
	}

	public static void setForStepExcel(ArrayList<Excel> forStepExcel) {
		ParametricCurve.forStepExcel = forStepExcel;
	}

	public static double getDivider() {
		return divider;
	}

	public static void setDivider(double divider) {
		ParametricCurve.divider = divider;
	}

	private static ArrayList<Excel> allexcel;
	private static ArrayList<Excel> forStepExcel;
	private static double masGnx[];
	private static double masMn[];
	private static double factor;
	private static double divider;
}
