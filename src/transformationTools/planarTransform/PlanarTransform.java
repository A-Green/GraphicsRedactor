package transformationTools.planarTransform;

import model.Excel;

public class PlanarTransform{	

	// ���������� 
	public static Excel move(int a, int b, Excel toMove)
	{
		return Move.move(a, b, toMove);
	}
	//������������
	public static Excel rotate(int f, Excel toRotate, Excel center)
	{
		return Rotate.rotate(f, toRotate, center);
	}
		
}
