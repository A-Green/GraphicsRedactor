package transformationTools.planarTransform;

import model.Excel;

public class PlanarTransform{	

	// перемещает 
	public static Excel move(int a, int b, Excel toMove)
	{
		return Move.move(a, b, toMove);
	}
	//поворачивает
	public static Excel rotate(int f, Excel toRotate, Excel center)
	{
		return Rotate.rotate(f, toRotate, center);
	}
		
}
