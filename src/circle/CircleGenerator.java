package circle;

import java.awt.Color;
import java.util.ArrayList;

import model.Excel;
import model.Grid;

public class CircleGenerator {

	public static ArrayList<Excel> circle(Grid g, int R)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
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

