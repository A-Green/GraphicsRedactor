package line;

import java.util.ArrayList;

import circle.CircleGenerator;

import model.Excel;

public class Circle extends AbstractLine {

	protected int R;
	public Circle(Excel ex, int R)
	{
		begin = ex;
		this.R = R;
	}
	
	@Override
	public ArrayList<Excel> getColoredExes() {
		// TODO Auto-generated method stub
		return CircleGenerator.circle(begin, R);
	}

	@Override
	public void move(Excel start, Excel end) {
		// TODO Auto-generated method stub
		if (start.getX() == begin.getX() && start.getY() == begin.getY())			
			begin = end;
	}

}
