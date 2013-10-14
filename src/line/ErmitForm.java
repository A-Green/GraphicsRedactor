package line;

import java.util.ArrayList;

import curveGeneration.CurveGenerator;

import model.Excel;

public class ErmitForm extends AbstractLine {

	protected Excel V1;
	protected Excel V2;
	public ErmitForm (Excel ex4, Excel ex3, Excel ex2, Excel ex1)
	{
		end = ex4;
		begin = ex1;
		V2 = ex3;
		V1 = ex2;
	
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		// TODO Auto-generated method stub
		return CurveGenerator.ErmitForm(end, V2, V1, begin);
	}

	@Override
	public void move(Excel start, Excel end) {
		// TODO Auto-generated method stub
		if (start.getX() == V1.getX() && start.getY() == V1.getY())			
			V1 = end;
		
		if (start.getX() == V2.getX() && start.getY() == V2.getY())
			V2 = end;
	}

}
