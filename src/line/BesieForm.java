package line;

import java.util.ArrayList;

import model.Excel;
import curveGeneration.BesieCurve;
import curveGeneration.ParametricCurve;

public class BesieForm extends AbstractLine{
	
	
ArrayList<Excel> allex = new ArrayList<Excel>();
	
	public BesieForm (ArrayList<Excel> allEx)
	{
		allex = allEx;
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

	}

	@Override
	protected void setColoredExes() {
		// TODO Auto-generated method stub
		ParametricCurve besie = new BesieCurve(allex);
		coloredEx = besie.Calculation();
	}

}
