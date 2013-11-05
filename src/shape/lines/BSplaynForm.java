package shape.lines;

import generation.curveGeneration.BSplaynCurve;
import generation.curveGeneration.ParametricCurve;

import java.util.ArrayList;

import model.Excel;

public class BSplaynForm extends AbstractLine{
	
	ArrayList<Excel> allex = new ArrayList<Excel>();
	
	public BSplaynForm (ArrayList<Excel> allEx)
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
		ParametricCurve bSplayn= new BSplaynCurve(allex);
		coloredEx = bSplayn.Calculation();
	}
	
	@Override
	public void dragg(Excel start, Excel finish)
	{
		
	}
	
	public void rotate(int angle)
	{
		
	}
	
	
}
