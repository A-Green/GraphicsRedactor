package line;

import java.util.ArrayList;

import model.Excel;
import curveGeneration.BSplaynCurve;
import curveGeneration.ParametricCurve;

public class BSplaynForm extends AbstractLine{
	
	ArrayList<Excel> allex = new ArrayList<Excel>();
	
	public BSplaynForm (ArrayList<Excel> allEx)
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
		ParametricCurve bSplayn= new BSplaynCurve(allex);
		coloredEx = bSplayn.Calculation();
	}
}
