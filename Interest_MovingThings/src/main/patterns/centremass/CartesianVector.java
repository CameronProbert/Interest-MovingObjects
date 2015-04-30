package main.patterns.centremass;

/**
 * The CartesianVector is a very simple, lightweight class that stores x and y
 * values to simulate a cartesian vector. It also allows for vector addition.
 * 
 * @author Cameron Probert
 *
 */
public class CartesianVector {

	private double x;
	private double y;

	public CartesianVector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public CartesianVector add(Orb... others) {
		double sumX = x;
		double sumY = y;
		for (Orb other : others) {
			sumX += other.getVector().getX();
			sumY += other.getVector().getY();
		}
		return new CartesianVector(sumX, sumY);
	}

	public CartesianVector add(CartesianVector... others) {
		double sumX = x;
		double sumY = y;
		for (CartesianVector other : others) {
			sumX += other.getX();
			sumY += other.getY();
		}
		return new CartesianVector(sumX, sumY);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
