package model;

import java.awt.Color;

public class Excel {
	
	private int z;
	private int t;
	private boolean colored;
	private Color color = Color.black;
	
	public Excel()
	{
		setColored(false);
		setT(0);
		setZ(0);
	}
	
	public Excel(Color col)
	{
		setT(0);
		setZ(0);
		setColor(col);
	}

	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color col)
	{
		color = col;
		setColored(true);
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
	
	
}
