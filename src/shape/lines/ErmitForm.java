package shape.lines;

import generation.curveGeneration.ErmitCurve;
import generation.curveGeneration.ParametricCurve;

import java.util.ArrayList;


import model.Excel;

public class ErmitForm extends AbstractLine {

ArrayList<Excel> allex = new ArrayList<Excel>();
	
	public ErmitForm (ArrayList<Excel> allEx)
	{
		allex = allEx;
		setColoredExes();
	}
	@Override
	public ArrayList<Excel> getColoredExes() {
		return coloredEx;
	}

	@Override
	public void move(Excel start, Excel finish) {
		for(int i=0;i<allex.size();i++)
		{
			if (start.getX() == allex.get(i).getX() && start.getY() == allex.get(i).getY())			
			{
				allex.remove(i);
				allex.add(i, finish);
				setColoredExes();
				break;
			}
		}

		for(Excel ex: coloredEx)
		{
			if (ex.equals(start))
			{	System.out.println("yes");
				dragg(start,finish);
				break;
			}
		}
	}

	@Override
	protected void setColoredExes() {
		ParametricCurve ermit = new ErmitCurve(allex);
		coloredEx = ermit.Calculation();
	}

	public void dragg(Excel start, Excel finish)
	{

	}
	
	public void rotate(int angle)
	{
		
	}
}
