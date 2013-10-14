package line;

import java.util.ArrayList;

import curveGeneration.BesieCurve;
import curveGeneration.ParametricCurve;

import model.Excel;

public class BesieForm extends AbstractLine{
	
	
	protected Excel V1;
	protected Excel V2;
	public BesieForm (Excel ex4, Excel ex3, Excel ex2, Excel ex1)
	{
		end = ex4;
		begin = ex1;
		V2 = ex3;
		V1 = ex2;
		setColoredExes();
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		// TODO Auto-generated method stub
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel finish) {
		// TODO Auto-generated method stub
		if (start.getX() == V1.getX() && start.getY() == V1.getY())			
		{
		V1 = finish;
		setColoredExes();
		}
	
	if (start.getX() == V2.getX() && start.getY() == V2.getY())
		{
		V2 = finish;
		setColoredExes();
		}
	
	if (start.getX() == begin.getX() && start.getY() == begin.getY())
		{
		begin = finish;
		setColoredExes();
		}
	
	if (start.getX() == end.getX() && start.getY() == end.getY())
		{
		end = finish;
		setColoredExes();
		}
		
	}

	@Override
	protected void setColoredExes() {
		// TODO Auto-generated method stub
		ParametricCurve besie= new BesieCurve(end, V2, V1, begin);
		coloredEx = besie.Calculation();
		//coloredEx = CurveGenerator.ErmitForm(end, V2, V1, begin);
	}

}
