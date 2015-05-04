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

	/**
	 * Creates a vector with the given values
	 * 
	 * @param x
	 * @param y
	 */
	public CartesianVector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds an array of CartesianVectors to this vector and returns the result
	 * 
	 * @param others
	 * @return
	 */
	public CartesianVector add(CartesianVector... others) {
		double sumX = x;
		double sumY = y;
		for (CartesianVector other : others) {
			sumX += other.getX();
			sumY += other.getY();
		}
		return new CartesianVector(sumX, sumY);
	}

	/**
	 * Returns the x component of the vector
	 * 
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y component of the vector
	 * 
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the x component of the vector
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y component of the vector
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Returns a representation of this vector in string format
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Cartesian Vector");
		sb.append(" || X: " + (int) x);
		sb.append(" || Y: " + (int) y);
		return sb.toString();
	}

}
