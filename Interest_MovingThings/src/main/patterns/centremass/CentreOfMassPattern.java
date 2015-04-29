package main.patterns.centremass;

import java.awt.Color;
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
	private double direction; // Direction in degrees
	private double velocity;

	List<Orb> orbs;

	public CentreOfMassPattern(double width, double height) {
		this.width = width;
		this.height = height;
		orbs = new ArrayList<Orb>();
		// initialiseBall();
		// initialiseCentreOfRotation();
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
		if (centreX < 0) {
			// Hits the left side (direction is between 180 and 0)
			direction = (180 - direction) % 360;
		} else if (centreX > width) {
			// Hits the right side (direction is between 0 and 180)
			direction = (180 - direction) % 360;
		} else if (centreY < 0) {
			// Hits the top (direction is between 270 and 90)
			direction = (360 - direction) % 360;
		} else if (centreY > height) {
			// Hits the bottom (direction is between 90 and 270)
			direction = (360 - direction) % 360;
		}
		// Move the ball
		centreX += velocity * Math.cos(Math.toRadians(direction));
		centreY += velocity * Math.sin(Math.toRadians(direction));
	}

}
