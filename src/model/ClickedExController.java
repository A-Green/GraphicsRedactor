package model;

import java.util.ArrayList;

public class ClickedExController {
	// ликнутые €чейки
	private ArrayList<Excel> exes = new ArrayList<Excel>();
	//возвращает последнюю €чейку массива и удал€ет еЄ из него
	private Excel popExcel()
	{
		Excel ex = null;
		if (!exes.isEmpty())
		{
			ex = exes.get(exes.size()-1);
			exes.remove(exes.size() -1);			
		}
		return ex;
	}
	//получить €чейку
	public Excel getExcel()
	{
		return popExcel();
	}
	//удал€ет, если есть така€ €чейка
	public void removeExcel(Excel ex)
	{
		for (Excel e: exes)
		{
			if (e.getX() == ex.getX() && e.getY() == ex.getY())
				{
				exes.remove(e);
				break;
				}
		}
	}
	//добавл€ет новую €чейку
	public void addExcel(Excel ex)
	{
		exes.add(ex);
	}
	//очищает массив клиунтых
	public void clear()
	{
		exes.clear();
	}
	// возвращает все €чейки
	public ArrayList<Excel> getClickedExes()
	{	
		return exes;
	}
	// возвращает все €чейки и удал€ет из exes
	public ArrayList<Excel> popAll()
	{	
		ArrayList<Excel> result = new ArrayList<Excel>(exes);
		clear();
		return result;
	}
	

}
