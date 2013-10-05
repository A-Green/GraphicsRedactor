package lineGeneration;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import model.Excel;
import model.Grid;

public class LineGenerator {
	
	public static ArrayList<Excel> DDA(Grid g)
	{
		ArrayList<Excel> result = new ArrayList<Excel>();
		
		System.out.println("���");
		int count = 0;
		Point p1 = new Point();
		Point p2 = new Point();
		int mas[] = { 0, 0, 0, 0 };
		int n = 0;
		for (int i = 0; i < g.getSize(); i++) {
			for (int j = 0; j < g.getSize(); j++) {
				if (g.getExcel(i, j).isColored() == true) {
					if (n < 4) {
						mas[n] = i;// *g.getStep();
						n++;
						mas[n] = j;// *g.getStep();
						n++;
					}
					count++;
				}
			}
		}

		if (count != 2) return null;
			else {
			
			p1.x = mas[0]; 
			p1.y = mas[1];
			p2.x = mas[2];
			p2.y = mas[3];
			
			
			int length;
			int modx = (int) (p2.getX() - p1.getX());
			
			if (modx < 0) {
				modx = modx * (-1);
			}
			
			int mody = (int) (p2.getY() - p1.getY());
			
			if (mody < 0) {
				mody = mody * (-1);
			}
			
			if (modx >= mody) {
				length = modx;
			} else {
				length = mody;
			}
			
			if (mody == 0) return horizontalLine(p1.x, p2.x, p1.y);
			if (modx == 0) return verticalalLine(p1.y, p2.y, p1.x);
			if (Math.abs(modx) == Math.abs(mody)) return diagonalLine(p1.x, p1.y, p2.x, p2.y);
			
			double dx = (p2.getX() - p1.getX()) / length;
			double dy = (p2.getY() - p1.getY()) / length;
			
			int signx = Sign(dx);
			int signy = Sign(dy);
			
			double x = p1.getX() + 0.5 * signx;
			double y = p1.getY() + 0.5 * signy;
			
			if(dx>=0 && dy>=0)
			{
				g.getExcel((int) x, (int) y).setColored(true); 
				int i = 0;
				while (i < length) {
				x = x + dx;
				y = y + dy;				
				result.add(new Excel((int) x,(int) y, Color.black));
				i++;
				}
			}
			else
			{
				g.getExcel((int) x, (int) y + 1).setColored(true); 
				int i = 0;
				while (i < length) {
				x = x + dx;
				y = y + dy;				
				result.add(new Excel((int) x,(int) y + 1, Color.black));
				i++;
			}
			}

		}
		
		return result;		
	}
	
	public static ArrayList<Excel> Brezenhem(Excel ex1, Excel ex2)
	{
		System.out.println("brezenhem");
		ArrayList<Excel> result = new ArrayList<Excel>();		

		if (ex1 == null || ex2 == null) return null;

			int x1 = ex1.getX();
			int y1 = ex1.getY();
			int x2 = ex2.getX();
			int y2 = ex2.getY();
			
			int dx = x2 - x1;     //�������� �� ��� x
			int dy = y2 - y1;     //�������� �� ��� y
			
			if (dy == 0) return horizontalLine(x1, x2, y1);
			if (dx == 0) return verticalalLine(y1, y2, x1);
			if (Math.abs(dx) == Math.abs(dy)) return diagonalLine(x1, y1, x2, y2);
			
			int incx = Sign(dx);  			
			 /*
	         * ����������, � ����� ������� ����� ����� ����������. ���� dx < 0, �.�. ������� ���
	         * ������ ������ �� x, �� incx ����� ����� -1.
	         * ��� ����� �������������� � ����� �����������.
	         */
			int incy = Sign(dy);
			 /*
	         * ����������. ���� ������ ������� ����� ����� -
	         * ��� ����� ������������� ����� ��� y (����� - �������������).
	         */
			
			int pdx, pdy,es,el;
			
			dx = Math.abs(dx); //����� �� ����� ����������: "if (dx < dy)"
			dy = Math.abs(dy); //������� ���������� ������� dx = |dx|; dy = |dy|
			
			if(dx > dy)			//���������� ������ �������:
			{
				/*
		          * ���� dx > dy, �� ������ ������� "�������" ����� ��� ���, �.�. �� ������ �������, ��� �������.
		          * ������ � ����� ����� ����� ���� �� ��� (������� el = dx;), ������ "�����������" ������ �� ����
		          * ���� � ������������ � ���, ����� ������� � ������ ������ ��� ��� (pdx = incx;), ��� ����
		          * �� y ����� ����� �����������.
		          */	
				pdx = incx;
				pdy = 0;
				es = dy;
				el = dx;			
			}
			else 	//������, ����� ������ ������ "�������", ��� �������, �.�. �������� �� ��� y
			{
				pdx = 0;
				pdy = incy;
				es = dx;
				el = dy; //����� � ����� ����� ��������� �� y
			}
			
			int x = x1;
			int y = y1;			
			int err = el;
			
			for (int i = 0; i < el; i++)
			{
				err -= 2 *es;    //��������� �� 2, ����� ������� �������� �������������
				
				if(err < 0)
				{
					err += 2*el; 	//��������� �� 2, ����� ������� �������� �������������
					x += incx; 		//�������� ������ (�������� ����� ��� ����, ���� ���� �������� �� �����)
					y += incy; 		//��� �������� �����-������, ���� ���� �������� �� y
				}
				else 
				{
					x += pdx;		//���������� ������ ������ ������, �.�. �������� ����� ��� ������, ����
					y += pdy;		//���� ��� �� ����; �������� ����� ��� ����, ���� �� y
				}

				result.add(new Excel(x,y,Color.black));
			}			
		
		return result;
	}

	public static ArrayList<Excel> WuAlgorithm (Excel ex1, Excel ex2)
	{
		ArrayList<Excel>result = new ArrayList<Excel>();	

		if (ex1 == null || ex2 == null) return null;		
			
			int x1 = ex1.getX();
			int y1 = ex1.getY();
			int x2 = ex2.getX();
			int y2 = ex2.getY();
			
			  double dx = x2 - x1;
			  double dy = y2 - y1;
			  
			  
			  //�������� �� ������� �����
			  if (dy == 0) return horizontalLine(x1, x2, y1);
			  if (dx == 0) return verticalalLine(y1, y2, x1);
			  if (Math.abs(dx) == Math.abs(dy)) return diagonalLine(x1, y1, x2, y2);
			  		  			  
			  if ( Math.abs(dx) > Math.abs(dy) ) { // ���� true, ������ ���������� ����� ���� �� ��� x
			    if ( x2 < x1 ) {   			
			      int temp = x1;				// ������ ������� ���������� ����� ���� ����� �������
			      x1 = x2;
			      x2 = temp;
			      
			      temp = y1;
			      y1 = y2;
			      y2 = temp;
			    }
			    double gradient = dy / dx;			// ���������� ���������. ����� �������������� ��� ��������� ����� �������
			    												//����� ���������� ���������� ������ �����

			    result.add(new Excel(x1,y1, Color.black));
			    												// � ������ ������
			    double intery = y1 + gradient;

			    result.add(new Excel(x2,y2, Color.black));   
			    
			 // �.� ���� ���������� ���� ������� ��� x, �� ��� �� ��� �� �. x1 �� �. �2, ��������� ����������, ������������� �����
			 // ��������� �������� ���������� ������� �� �������������� �����, ������������ ������������� �����
			    for(int x = x1 + 1; x <= (x2 - 1); x++) 						    										
			    {			    	
			    	 result.add(new Excel(x,(int) intery, getColor(rfpart(intery))));
			    	 result.add(new Excel(x,(int) intery + 1, getColor(fpart(intery))));
			    	 intery += gradient;
			    }
			  } else {									// ������� ���������� ���, ��� ��� ������ if, ������ ������ 
			    if ( y2 < y1 ) {						// ������� ���� ���������� ��� y
			    
			      int temp = x1;
			      x1 = x2;
			      x2 = temp;
			      
			      temp = y1;
			      y1 = y2;
			      y2 = temp;
			    }
			    
			    double gradient = dx / dy;
			    
			    result.add(new Excel(x1,y1, Color.black));
			    
			    double interx = x1 + gradient;

			    result.add(new Excel(x2,y2, Color.black));
			    
			    int y;
			    for(y=y1+1; y <= (y2-1); y++) 
			    {	    	
			    	result.add(new Excel((int) interx, y, getColor(rfpart(interx))));
			    	result.add(new Excel((int) interx + 1, y, getColor(fpart(interx))));
			    	interx += gradient;
			    }
			  }
			
			
	         					
		return result;
		
	}

	private static int Sign(double x)
	{
		if (x < 0) return -1;
		else if (x > 0) return 1;
		return 0;
	}
	
	private static int ipart(double x)
	{
		return (int) x;
	}
	
	private static double fpart(double x)
	{
		return x - ipart(x);
	}
	
	private static double rfpart(double x)
	{
		return 1 - fpart(x);
	}
	
	private static Color getColor(double rate)
	{
		if (rate >= 0.7)
			return Color.black;
		if (rate < 0.7 && rate >= 0.5)
			return Color.black.brighter();
		if (rate < 0.5 && rate >= 0.4)
			return Color.gray;
		if (rate < 0.4 && rate >=0.3)
			return Color.gray.brighter();
		if (rate < 0.3 && rate >= 0.1)
			return Color.lightGray;		
		return null;				
	}
	
	
	public static ArrayList<Excel> horizontalLine(int x1, int x2, int y)
	{	
		System.out.println("horizotal line");
		ArrayList<Excel> result = new ArrayList<Excel>();
		int x = (x1 > x2) ? x2 : x1;
		int length = Math.abs(x1 - x2);
		
		for (int i = 0; i < length; i++)
			result.add(new Excel(x + i, y, Color.black));
		
		return result;
	}
	
	public static ArrayList<Excel> verticalalLine(int y1, int y2, int x)
	{
		System.out.println("vertical line");
		ArrayList<Excel> result = new ArrayList<Excel>();
		int y = (y1 > y2) ? y2 : y1;
		int length = Math.abs(y1 - y2);
		
		for (int i = 0; i < length; i++)
			result.add(new Excel(x, y + i, Color.black));
		
		return result;
	}
	
	public static ArrayList<Excel> diagonalLine(int x1, int y1, int x2, int y2)
	{	
		System.out.println("diagonal line");
		ArrayList<Excel> result = new ArrayList<Excel>();	
		int increm;
		
		if (x1 < x2) 
		{
			increm = (y1 < y2) ? 1 : -1;
			
			for (int x = x1; x < x2; x++, y1+=increm )
			{
				result.add(new Excel(x, y1, Color.black));
			}

		}
		else 
		{
			increm = (y1 < y2) ? -1 : 1;
			
			for (int x = x2; x < x1; x++, y2+=increm )
			{
				result.add(new Excel(x, y2, Color.black));
			}
		}
				
		return result;		
	}
	
}

	