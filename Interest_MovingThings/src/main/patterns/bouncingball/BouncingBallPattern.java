package main.patterns.bouncingball;

import java.awt.Graphics;
import java.awt.Color;

import main.patterns.DrawingPattern;

public class BouncingBallPattern extends DrawingPattern {

	// Bounds
	private double width;
	private double height;

	// The ball
	private Color colour;
	private double radius;
	private double diameter;
	private double centreX;
	private double centreY;
	private double direction;
	private double velocity;

	public BouncingBallPattern(double width, double height) {
		this.width = width;
		this.height = height;
		radius = (Math.random() * 10) + 25; // radius is 25-35 in size
		diameter = radius * 2;
		centreX = (Math.random() * (width - diameter - 4)) + radius + 1;
		centreY = (Math.random() * (height - diameter - 4)) + radius + 1;
		colour = generateRandomColor(Color.WHITE);
		velocity = Math.random() * 5 + 5;
		direction = Math.random() * 360;
	}

	private void stepBall() {
		// Decides if the direction should be changed (hits the wall)
		if (centreX - radius < 0) {
			// Hits the left side (direction is between 180 and 0)
			direction = (180 - direction) % 360;
			colour = generateRandomColor(Color.WHITE);
		} else if (centreX + radius > width) {
			// Hits the right side (direction is between 0 and 180)
			direction = (180 - direction) % 360;
			colour = generateRandomColor(Color.WHITE);
		} else if (centreY - radius < 0) {
			// Hits the top (direction is between 270 and 90)
			direction = (360 - direction) % 360;
			colour = generateRandomColor(Color.WHITE);
		} else if (centreY + radius > height) {
			// Hits the bottom (direction is between 90 and 270)
			direction = (360 - direction) % 360;
			colour = generateRandomColor(Color.WHITE);
		}
		// Move the ball
		centreX += velocity * Math.cos(Math.toRadians(direction));
		centreY += velocity * Math.sin(Math.toRadians(direction));
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
