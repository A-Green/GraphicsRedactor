package transformationTools.dimensionalTrasform;

import java.util.ArrayList;

import model.Excel;

public class DimensionalTrasform {
	
	public static ArrayList<Excel> scale(int coeff, ArrayList<Excel> toScale)
	{
		 return Scalign.scale(coeff, toScale);
	}
	
	public static ArrayList<Excel> move(int a,int b,int c, ArrayList<Excel> toScale)
	{
		 return Move.move(a,b,c, toScale);
	}
	
	public static ArrayList<Excel> rotateX(int angle, ArrayList<Excel> toScale)
	{
		 return Rotate.rotateX(angle, toScale);
	}
	
	public static ArrayList<Excel> rotateY(int angle, ArrayList<Excel> toScale)
	{
		 return Rotate.rotateY(angle, toScale);
	}
	
	public static ArrayList<Excel> rotateZ(int angle, ArrayList<Excel> toScale)
	{
		 return Rotate.rotateZ(angle, toScale);
	}

}
