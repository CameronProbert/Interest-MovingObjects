package main.patterns.centremass;

import java.awt.Color;
import java.awt.Graphics;

import main.patterns.DrawingPattern;

public class Orb {

	private static final double REPEL_MAGNITUDE = 20000;

	private double centreX;
	private double centreY;
	private double magnitude;
	private CartesianVector vector;
	private Color colour;

	public Orb(double centreX, double centreY) {
		this.centreX = centreX;
		this.centreY = centreY;
		double velocity = Math.random() * 5 + 5;
		this.vector = new CartesianVector(Math.sin(velocity),
				Math.cos(velocity));
		magnitude = Math.random() * 7.5 + 5;
		this.colour = DrawingPattern.generateRandomMixedColor(Color.white);
	}

	public void step(Orb[] otherOrbs, CartesianVector centreAttraction) {
		for (Orb otherOrb : otherOrbs) {
			// setVector(this.vector.add(otherOrbs));
			double repelX = Math.pow((centreX - otherOrb.getCentreX())
					/ REPEL_MAGNITUDE, 2);
			if (centreX < otherOrb.getCentreX()) {
				repelX *= -1;
			}
			double repelY = Math.pow((centreY - otherOrb.getCentreY())
					/ REPEL_MAGNITUDE, 2);
			if (centreY < otherOrb.getCentreY()) {
				repelY *= -1;
			}
			setVector(this.vector.add(new CartesianVector(repelX, repelY)));
		}
		setVector(this.vector.add(centreAttraction));
		centreX += vector.getX();
		centreY += vector.getY();
	}

	public void draw(Graphics g) {
		g.setColor(colour);
		double radius = magnitude;
		g.fillOval((int) (centreX - radius), (int) (centreY - radius),
				(int) radius * 2, (int) radius * 2);
	}

	public double getCentreX() {
		return centreX;
	}

	public double getCentreY() {
		return centreY;
	}

	public CartesianVector getVector() {
		return vector;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public void setCentreX(double centreX) {
		this.centreX = centreX;
	}

	public void setCentreY(double centreY) {
		this.centreY = centreY;
	}

	public void setVector(CartesianVector vector) {
		this.vector = vector;
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

}
