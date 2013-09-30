package circle;

import java.awt.Point;
import java.util.ArrayList;

import model.Grid;

public class CircleGenerator {

	public static ArrayList<Point> circle(Grid g, int R)
	{
		ArrayList<Point> result = new ArrayList<Point>();
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
		int gridSize = g.getSize();
		
		if (R > 0)
		{
			if (checkBounds(x + dx, dy + y , gridSize))
		result.add(new Point(x + dx,dy + y));
			if (checkBounds(x + dx, dy - y , gridSize))
		result.add(new Point(x + dx,dy - y));
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
			
			if (checkBounds(x + dx, dy + y , gridSize))
				result.add(new Point(dx + x,dy + y)); // ������� ����� ������������ ������			
			if (checkBounds(x + dx, dy - y , gridSize))
				result.add(new Point(dx + x,dy - y));			
			if (checkBounds(dx - x, dy + y , gridSize))
				result.add(new Point(dx - x,dy + y));			
			if (checkBounds(dx - x, dy - y , gridSize))
				result.add(new Point(dx - x,dy - y));
			
		}
	
		return result;		
	}
	
	private static boolean checkBounds(int x, int y, int n)
	{
		if (x < 0 || y < 0) return false;
		if (x >= n || y >= n) return false; 
		return true;
	}
}

