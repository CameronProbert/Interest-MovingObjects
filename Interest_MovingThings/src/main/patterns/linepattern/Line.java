package main.patterns.linepattern;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A simple line that has an x and y for each end and a draw method
 * 
 * @author Cameron Probert
 *
 */
public class Line {

	// Point 1
	private double x1;
	private double y1;

	// Point 2
	private double x2;
	private double y2;

	private Color colour;

	/**
	 * Creates a line with the given parameters
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param colour
	 */
	public Line(double x1, double y1, double x2, double y2, Color colour) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.colour = colour;
	}

	/**
	 * Draws the line
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(colour);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}
	
	/**
	 * Returns the x1 value of the line
	 * @return
	 */
	public double getX1(){
		return x1;
	}
	
	/**
	 * Returns the y1 value of the line
	 * @return
	 */
	public double getY1(){
		return y1;
	}
	
	/**
	 * Returns the x2 value of the line
	 * @return
	 */
	public double getX2(){
		return x2;
	}
	
	/**
	 * Returns the y2 value of the line
	 * @return
	 */
	public double getY2(){
		return y2;
	}
	
	/**
	 * Returns the y2 value of the line
	 * @return
	 */
	public Color getColour(){
		return colour;
	}
	
	/**
	 * Returns a string that represents the line
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Line:");
		sb.append("\nX1: " + (int) x1);
		sb.append(" || Y1: " + (int) y1);
		sb.append("\nX2: " + (int) x2);
		sb.append(" || Y2: " + (int) y2);
		return sb.toString();
	}
}
