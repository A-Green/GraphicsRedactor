package line;

import java.util.ArrayList;

import lineGeneration.LineGenerator;
import model.Excel;

public class BrezenhemLine extends DDALine{

	public BrezenhemLine(Excel ex1, Excel ex2) {
		super(ex1, ex2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Excel> getColoredExes() {
		// TODO Auto-generated method stub
		return LineGenerator.Brezenhem(begin, end);
	}

}
