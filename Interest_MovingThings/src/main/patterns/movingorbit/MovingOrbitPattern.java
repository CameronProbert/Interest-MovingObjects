package main.patterns.movingorbit;

import java.awt.Color;
import java.awt.Graphics;

import main.patterns.DrawingPattern;

public class MovingOrbitPattern extends DrawingPattern {

	private static final double BUFFER_SIZE = 70;
	private static final double ORBIT_DIST = BUFFER_SIZE;

	// Bounds
	private double width;
	private double height;
	private double buffer;

	// The centre of mass
	private double corCentreX;
	private double corCentreY;
	private double corDirection;
	private double corVelocity;

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
		corVelocity = Math.random() * 10 + 2.5;
		corDirection = Math.random() * 360;
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
		if (corCentreX - ballRadius < buffer) {
			// Hits the left side (direction is between 180 and 0)
			corDirection = (180 - corDirection) % 360;
			ballColour = generateRandomMixedColor(Color.WHITE);
		} else if (corCentreX + ballRadius > width - buffer) {
			// Hits the right side (direction is between 0 and 180)
			corDirection = (180 - corDirection) % 360;
			ballColour = generateRandomMixedColor(Color.WHITE);
		} else if (corCentreY - ballRadius < buffer) {
			// Hits the top (direction is between 270 and 90)
			corDirection = (360 - corDirection) % 360;
			ballColour = generateRandomMixedColor(Color.WHITE);
		} else if (corCentreY + ballRadius > height - buffer) {
			// Hits the bottom (direction is between 90 and 270)
			corDirection = (360 - corDirection) % 360;
			ballColour = generateRandomMixedColor(Color.WHITE);
		}
		// Move the centre of rotation
		corCentreX += corVelocity * Math.cos(Math.toRadians(corDirection));
		corCentreY += corVelocity * Math.sin(Math.toRadians(corDirection));

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
