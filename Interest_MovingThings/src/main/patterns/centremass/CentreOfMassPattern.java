package main.patterns.centremass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.patterns.DrawingPattern;

public class CentreOfMassPattern extends DrawingPattern {

	private double width;
	private double height;

	// Centre of Mass
	private double centreX;
	private double centreY;
	private double velocity;
	private CartesianVector centreVector;

	private List<Orb> orbs;

	public CentreOfMassPattern(double width, double height) {
		this.width = width;
		this.height = height;
		orbs = new ArrayList<Orb>();
		initialiseCentre();
		initialiseOrbs();
	}

	private void initialiseCentre() {
		centreX = Math.random() * (width - 1) + 1;
		centreY = Math.random() * (height - 1) + 1;
		velocity = Math.random() * 5 + 5;
		centreVector = new CartesianVector(Math.cos(velocity), Math.sin(velocity));
	}

	private void initialiseOrbs() {
		int numOrbs = (int) (Math.random() * 5 + 5);
		for (int i = 0; i < numOrbs; i++) {
			double orbX = Math.random() * (width - 1) + 1;
			double orbY = Math.random() * (height - 1) + 1;
			orbs.add(new Orb(orbX, orbY));
		}
	}

	@Override
	public void step() {
		stepCentre();
		for (Orb orb : orbs) {

			Orb[] otherOrbs = new Orb[this.orbs.size() - 1];
			int indexModifier = 0;
			for (int i = 0; i < otherOrbs.length; i++) {
				if (orb != orbs.get(i)) {
					otherOrbs[i] = orbs.get(i - indexModifier);
				} else {
					indexModifier = -1;
				}
			}
			orb.step(otherOrbs);
		}
	}

	@Override
	public void draw(Graphics g) {
		for (Orb orb : orbs) {
			orb.draw(g);
		}
	}

	private void stepCentre() {
		// Decides if the direction should be changed (hits the wall)
		if (centreX < 0 || centreX > width) {
			// Hits the left or right side
			centreVector.setX(-centreVector.getX());
		} else if (centreY < 0 || centreY > height) {
			// Hits the top or bottom
			centreVector.setY(-centreVector.getY());
		}
		// Move the ball
		centreX += centreVector.getX();
		centreY += centreVector.getY();
	}

}
