package circle;

import java.awt.Color;
import java.util.ArrayList;

import model.Excel;

public class CircleGenerator {

	public static ArrayList<Excel> circle(Excel ex, int R)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
		if (ex == null) return null;
		int dx = ex.getX();
		int dy = ex.getY();

		result.add(new Excel(dx,dy, null));
		
		int limit = 0;
		int x = 0;
		int y = R;
		double delta = 2 - 2 * R;       //�������������� �������� ������
		
		if (R > 0)
		{
		result.add(new Excel(x + dx,dy + y, Color.black));
		result.add(new Excel(x + dx,dy - y, Color.black));
		}
		
		while(y > limit)
		{
			if (delta > 0)							// ������������ ����� ����� ��� ����������. ����� ������� ����� ������������ � ������������ ��������
			{
				double sigma = 2*delta - 2*x - 1; // ������������� ���������� �� ���������� �� �������
				
				if (sigma <= 0)						// ���� �����, ������, ���������� �� ��-�� �� ������������� ������� ������ ��� �����, 
				{									// �������� ������������ 
					x += 1;
					y -= 1;
					delta = delta + 2*x - 2*y + 2;					
				}
				else							// ����� ��������, �������� ������������ �������
				{
					y -= 1;
					delta = delta - 2*y + 1;
				}
			}
			
			else if (delta == 0)				// ������, ����� ������������ ������� ����� ����� �� ����������, �������� ���
			{
				x += 1;
				y -= 1;
				delta = delta + 2*x - 2*y + 2;	
			}
			else 								  // ������, ����� ������������ ����� ����� ������ ����������. 
			{									  // ����� ������� ����� �������������� � ������������ ��������
				double sigma = 2*delta + 2*y - 1; // ������������� ���������� �� ���������� �� ���������� �������
				
				if (sigma > 0)						// ���������� �� ��������������� ������� ������, ���� ������������
				{
					x += 1;
					y -= 1;
					delta = delta + 2*x - 2*y + 2;
				}
				else								// ����� ���� �������������� �������
				{
					x +=1;
					delta = delta + 2*x + 1;
				}
			}
			
				result.add(new Excel(dx + x,dy + y, Color.black)); // ������� ����� ������������ ������				
				result.add(new Excel(dx + x,dy - y, Color.black));					
				result.add(new Excel(dx - x,dy + y, Color.black));						
				result.add(new Excel(dx - x,dy - y, Color.black));
			
		}
	
		return result;		
	}	
}

