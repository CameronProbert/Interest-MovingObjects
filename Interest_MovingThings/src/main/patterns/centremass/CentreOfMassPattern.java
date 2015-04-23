package main.patterns.centremass;

import java.awt.Graphics;

import main.patterns.DrawingPattern;

public class CentreOfMassPattern extends DrawingPattern {

	private double width;
	private double height;

	// Centre of Mass
	private double x;
	private double y;
	private double direction; // Direction in degrees
	private double velocity;

	// Orb 1
	private double orb1CX;
	private double orb1CY;
	private double orb1Dir;
	private double orb1Vel;
	// Orb 2
	// Orb 3
	// Orb 4
	// Orb 5

	public CentreOfMassPattern(double width, double height) {
		this.width = width;
		this.height = height;
		initialiseBall();
		initialiseCentreOfRotation();
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
