package shape.lines;

import generation.curveGeneration.BesieCurve;
import generation.curveGeneration.ParametricCurve;

import java.util.ArrayList;

import model.Excel;

public class BesieForm extends AbstractLine{
	
	
ArrayList<Excel> allex = new ArrayList<Excel>();
	
	public BesieForm (ArrayList<Excel> allEx)
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

		dragg(start, finish);
	}


	@Override
	protected void setColoredExes() {
		ParametricCurve besie = new BesieCurve(allex);
		coloredEx = besie.Calculation();
	}
	
	public void dragg(Excel start, Excel finish)
	{
		
	}
	
	public void rotate(int angle)
	{
		
	}

}
