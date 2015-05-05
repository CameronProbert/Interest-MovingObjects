package main.patterns.bouncingball;

import java.awt.Graphics;
import java.awt.Color;

import main.patterns.DrawingPattern;
import main.patterns.utility.CartesianVector;

public class BouncingBallPattern extends DrawingPattern {

	// The ball
	private Color colour;
	private double radius;
	private double diameter;
	private double centreX;
	private double centreY;
	private CartesianVector vector;

	public BouncingBallPattern(double width, double height) {
		super(width, height);
		initialiseBall();
	}

	private void initialiseBall() {
		radius = (Math.random() * 10) + 25; // radius is 25-35 in size
		diameter = radius * 2;
		centreX = (Math.random() * (width - diameter - 4)) + radius + 1;
		centreY = (Math.random() * (height - diameter - 4)) + radius + 1;
		colour = generateRandomMixedColor(Color.WHITE);
		double velocity = Math.random() * 20 + 10;
		double direction = Math.random() * 2 * Math.PI;
		vector = new CartesianVector(velocity * Math.cos(direction), velocity
				* Math.sin(direction));
		System.out.println(vector.toString());
	}

	private void stepBall() {
		// Decides if the direction should be changed (hits the wall)
		if (centreX - radius < 0 || centreX + radius > width) {
			// Hits the left or right side
			vector.setX(-vector.getX());
		} else if (centreY - radius < 0 || centreY + radius > height) {
			// Hits the top or bottom
			vector.setY(-vector.getY());
		}
		// Move the ball
		centreX += vector.getX();
		centreY += vector.getY();
	}

	@Override
	public void step() {
		stepBall();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(colour);
		g.fillOval((int) centreX - (int) radius, (int) centreY - (int) radius,
				(int) radius * 2, (int) radius * 2);
	}

}
