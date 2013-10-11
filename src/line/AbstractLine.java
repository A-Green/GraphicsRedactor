package line;

import java.util.ArrayList;

import model.Excel;

public abstract class AbstractLine {

	protected Excel begin;
	protected Excel end;
	protected ArrayList<Excel> coloredEx;
	
	public abstract ArrayList<Excel> getColoredExes();

	public abstract void move(Excel start, Excel end);
}
