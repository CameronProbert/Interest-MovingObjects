package main.patterns.movingorbit;

import java.awt.Color;
import java.awt.Graphics;

import main.patterns.DrawingPattern;
import main.patterns.utility.CartesianVector;

public class MovingOrbitPattern extends DrawingPattern {

	private static final double BUFFER_SIZE = 70;
	private static final double ORBIT_DIST = BUFFER_SIZE;

	// Bounds
	private double buffer;

	// The centre of mass
	private double corCentreX;
	private double corCentreY;
	private CartesianVector corVector;

	// The ball
	private double ballDirection;
	private double ballRadius;
	private double ballDiameter;
	private double rotationSpeed;
	private Color ballColour;

	public MovingOrbitPattern(double width, double height) {
		super(width, height);
		initialiseBall();
		initialiseCentreOfRotation();
	}

	private void initialiseCentreOfRotation() {
		corCentreX = (Math.random() * (width - ballDiameter - 4 - buffer * 2))
				+ ballRadius + buffer + 1;
		corCentreY = (Math.random() * (height - ballDiameter - 4 - buffer * 2))
				+ ballRadius + buffer + 1;
		double corVelocity = Math.random() * 20 + 5;
		double direction = Math.random() * 2 * Math.PI;
		corVector = new CartesianVector(corVelocity * Math.cos(direction),
				corVelocity * Math.sin(direction));
	}

	private void initialiseBall() {
		ballRadius = (Math.random() * 10) + 25; // radius is 25-35 in size
		ballDiameter = ballRadius * 2;
		buffer = ballRadius + BUFFER_SIZE;
		ballColour = generateRandomMixedColor(Color.WHITE);
		ballDirection = Math.random() * 360;
		rotationSpeed = Math.random() * 5 + 5;
	}

	private void stepBall() {
		// Decides if the direction should be changed (hits the wall)
		if (corCentreX - ballRadius < buffer
				|| corCentreX + ballRadius > width - buffer) {
			// Hits the left or right side
			corVector.setX(-corVector.getX());
		} else if (corCentreY - ballRadius < buffer
				|| corCentreY + ballRadius > height - buffer) {
			// Hits the top or bottom
			corVector.setY(-corVector.getY());
		}
		// Move the ball
		corCentreX += corVector.getX();
		corCentreY += corVector.getY();

		// Move the ball
		ballDirection += rotationSpeed;
	}

	@Override
	public void step() {
		stepBall();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(ballColour);
		double centreX = corCentreX + ORBIT_DIST
				* Math.cos(Math.toRadians(ballDirection));
		double centreY = corCentreY + ORBIT_DIST
				* Math.sin(Math.toRadians(ballDirection));
		g.fillOval((int) centreX - (int) ballRadius, (int) centreY
				- (int) ballRadius, (int) ballRadius * 2, (int) ballRadius * 2);
	}

}
