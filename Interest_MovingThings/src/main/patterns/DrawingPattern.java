package main.patterns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class DrawingPattern {
	
	protected double width;
	protected double height;
	
	public DrawingPattern(double width, double height){
		this.width = width;
		this.height = height;
	}

	/**
	 * Do processing for a frame of the pattern
	 */
	public abstract void step();

	/**
	 * Draw the pattern
	 */
	public abstract void draw(Graphics g);
	
	public static Color generateRandomMixedColor(Color mix) {
	    Random random = new Random();
	    int red = random.nextInt(256);
	    int green = random.nextInt(256);
	    int blue = random.nextInt(256);

	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2;
	        green = (green + mix.getGreen()) / 2;
	        blue = (blue + mix.getBlue()) / 2;
	    }

	    Color color = new Color(red, green, blue);
	    return color;
	}
}
