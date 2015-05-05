package main.patterns.utility;

import java.awt.Color;
import java.awt.Graphics;

import main.patterns.DrawingPattern;

/**
 * An Orb is a component of the CentreOfMassPattern. It contains a centre x and
 * y point, a magnitude from which it gets its radius when drawing, a colour and
 * a vector that represents the direction and velocity it is currently moving
 * in.
 * 
 * @author Cameron Probert
 *
 */
public class Orb {

	// The higher this value the LESS they repel each other
	private static final double REPEL_MAGNITUDE = 15000;

	private double centreX;
	private double centreY;
	private double magnitude;
	private CartesianVector vector;
	private Color colour;

	/**
	 * Creates a new orb at the given x and y coordinates
	 * 
	 * @param centreX
	 * @param centreY
	 */
	public Orb(double centreX, double centreY) {
		this.centreX = centreX;
		this.centreY = centreY;
		double velocity = Math.random() * 5 + 5;
		this.vector = new CartesianVector(Math.sin(velocity),
				Math.cos(velocity));
		magnitude = Math.random() * 10 + 10;
		this.colour = DrawingPattern.generateRandomMixedColor(Color.white);
	}

	/**
	 * Moves the orb depending on the locations of the other orbs and a vector
	 * that represents this orbs attraction force towards the centre of mass.
	 * 
	 * @param otherOrbs
	 * @param centreAttraction
	 */
	public void step(Orb[] otherOrbs, CartesianVector centreAttraction) {

		// For each orb modify the vector based on the distance between the orbs
		for (Orb otherOrb : otherOrbs) {
			double repelX = Math
					.sqrt(Math.abs(centreX - otherOrb.getCentreX()))
					/ REPEL_MAGNITUDE;
			if (centreX < otherOrb.getCentreX()) {
				repelX *= -1;
			}
			double repelY = Math
					.sqrt(Math.abs(centreY - otherOrb.getCentreY()))
					/ REPEL_MAGNITUDE;
			if (centreY < otherOrb.getCentreY()) {
				repelY *= -1;
			}
			setVector(this.vector.add(new CartesianVector(repelX, repelY)));
		}

		// Modify the vector based on the centre attraction
		setVector(this.vector.add(centreAttraction));

		// Move the orb
		centreX += vector.getX();
		centreY += vector.getY();
	}

	/**
	 * Draws the orb
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(colour);
		double radius = magnitude;
		g.fillOval((int) (centreX - radius), (int) (centreY - radius),
				(int) radius * 2, (int) radius * 2);
	}

	/**
	 * Returns the centreX of the orb
	 * 
	 * @return
	 */
	public double getCentreX() {
		return centreX;
	}

	/**
	 * Returns the centreY of the orb
	 * 
	 * @return
	 */
	public double getCentreY() {
		return centreY;
	}

	/**
	 * Returns the vector of the orb
	 * 
	 * @return
	 */
	public CartesianVector getVector() {
		return vector;
	}

	/**
	 * Returns the magnitude of the orb
	 * 
	 * @return
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Sets the centreX of the orb to the given value
	 * 
	 * @param centreX
	 */
	public void setCentreX(double centreX) {
		this.centreX = centreX;
	}

	/**
	 * Sets the centreY of the orb to the given value
	 * 
	 * @param centreY
	 */
	public void setCentreY(double centreY) {
		this.centreY = centreY;
	}

	/**
	 * Sets the vector of the orb to the given value
	 * 
	 * @param vector
	 */
	public void setVector(CartesianVector vector) {
		this.vector = vector;
	}

	/**
	 * Sets the magnitude of the orb to the given value
	 * 
	 * @param magnitude
	 */
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	/**
	 * Returns a string that represents this orb
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Orb:");
		sb.append("\nCentre X: " + (int) centreX + " || Centre Y: "
				+ (int) centreY);
		sb.append("\n" + vector.toString());
		return sb.toString();
	}

}
