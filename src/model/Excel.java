package model;

import java.awt.Color;

public class Excel {
	
	private int x;
	private int y;
	private int z;
	private int t;
	private boolean colored;
	private Color color = Color.black;
	
	public Excel(int x, int y)
	{
		setColored(false);
		setX(x);
		setY(y);
		setT(1);
		setZ(1);
	}
	
	public Excel(int x, int y,Color col)
	{
		setX(x);
		setY(y);
		setT(1);
		setZ(1);
		setColor(col);
	}
	
	public Excel(int x, int y, int z, Color col)
	{
		setX(x);
		setY(y);
		setT(1);
		setZ(z);
		setColor(col);
	}

	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color col)
	{
		if (col != null)
		{
		color = col;
		setColored(true);
		}
	}
	
	public int getX()
	{
		return this.x; 
	}
	
	public int getY()
	{
		return this.y; 
	}
	
	public void setX(int x)
	{
		this.x = x; 
	}
	
	public void setY(int y)
	{
		this.y = y; 
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public boolean isColored() {
		return colored;
	}
	public void setColored(boolean colored) {
		this.colored = colored;
		if(!colored) this.color = Color.black; // установить цвет по умолчанию
	}
	
	public void setColored(Color col) {
		this.colored = true;
		setColor(col);
	}
	
	public String toString()
	{
		return "X:" + x + " Y:" + y + " Color: " + ((colored)? getColor().toString() : "no");
	}
	
	
}
