package main.patterns.centremass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.patterns.DrawingPattern;
import main.patterns.utility.CartesianVector;
import main.patterns.utility.Orb;

/**
 * The CentreOfMassPattern contains an invisible centre point with multiple
 * other orbs attracted to the centre. The other orbs also repel each other
 * slightly.
 * 
 * @author Cameron Probert
 *
 */
public class CentreOfMassPattern extends DrawingPattern {

	private static final double ATTRACT_MAGNITUDE = 1000;
	private static final int MIN_NUM_ORBS = 5;
	private static final int NUM_ORB_VARIANCE = 6;

	private double paddingX;
	private double paddingY;

	// Centre of Mass
	private double centreX;
	private double centreY;
	private CartesianVector centreVector;

	// A list of all the orbs in the pattern
	private List<Orb> orbs;

	/**
	 * Creates the Centre of Mass Pattern and sets the boundaries to the given
	 * width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public CentreOfMassPattern(double width, double height) {
		super(width, height);
		paddingX = width / 5;
		paddingY = height / 5;
		orbs = new ArrayList<Orb>();
		initialiseCentre();
		initialiseOrbs();
	}

	/**
	 * Creates the invisible centre value.
	 */
	private void initialiseCentre() {
		centreX = Math.random() * (width - paddingX * 4) + paddingX * 2;
		centreY = Math.random() * (height - paddingY * 4) + paddingY * 2;
		double velocity = Math.random() * 10 + 5;
		double direction = Math.random() * 2 * Math.PI;
		centreVector = new CartesianVector(velocity * Math.cos(direction),
				velocity * Math.sin(direction));
	}

	/**
	 * Creates a number of orbs
	 */
	private void initialiseOrbs() {
		int numOrbs = (int) (Math.random() * NUM_ORB_VARIANCE + MIN_NUM_ORBS);
		for (int i = 0; i < numOrbs; i++) {
			double orbX = centreX + (Math.random() * paddingX - paddingX / 2);
			double orbY = centreY + (Math.random() * paddingY - paddingY / 2);
			orbs.add(new Orb(orbX, orbY));
		}
	}

	/**
	 * Increments the state of the pattern (i.e. moves orbs and centre)
	 */
	@Override
	public void step() {
		stepCentre();
		// System.out.println("CentreX: " + (int) centreX + " || CentreY: "
		// + (int) centreY);
		for (Orb orb : orbs) {
			// System.out.println("Vector = " + orb.getVector().toString());

			Orb[] otherOrbs = new Orb[this.orbs.size() - 1];
			int indexModifier = 0;

			// Add every orb but the current one in the for loop to the
			// otherOrbs array
			for (int i = 0; i < orbs.size(); i++) {
				if (orb != orbs.get(i)) {
					otherOrbs[i - indexModifier] = orbs.get(i);
				} else {
					indexModifier = 1;
				}
			}
			double attractX = Math.pow((centreX - orb.getCentreX())
					/ ATTRACT_MAGNITUDE, 2);
			if (centreX < orb.getCentreX()) {
				attractX *= -1;
			}
			double attractY = Math.pow((centreY - orb.getCentreY())
					/ ATTRACT_MAGNITUDE, 2);
			if (centreY < orb.getCentreY()) {
				attractY *= -1;
			}
			CartesianVector centreAttraction = new CartesianVector(attractX,
					attractY);
			orb.step(otherOrbs, centreAttraction);
		}
	}

	/**
	 * Draw the state of the pattern
	 */
	@Override
	public void draw(Graphics g) {
		// g.setColor(Color.WHITE);
		// g.fillOval((int) (centreX - 2), (int) (centreY - 2), 4, 4);
		for (Orb orb : orbs) {
			orb.draw(g);
		}
	}

	/**
	 * Moves the centre of the pattern
	 */
	private void stepCentre() {
		// Decides if the direction should be changed (hits the wall)
		if (centreX - paddingX * 2 < 0 || centreX + paddingY * 2 > width) {
			// Hits the left or right side
			centreVector.setX(-centreVector.getX());
		} else if (centreY - paddingY * 2 < 0
				|| centreY + paddingY * 2 > height) {
			// Hits the top or bottom
			centreVector.setY(-centreVector.getY());
		}
		// Move the ball
		centreX += centreVector.getX();
		centreY += centreVector.getY();
	}

}
