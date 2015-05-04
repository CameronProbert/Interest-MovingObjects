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
			if (other != null) {
				sumX += 10/other.getCentreX();
				sumY += 10/other.getCentreY();
			}
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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Cartesian Vector");
		sb.append(" || X: " + (int)x);
		sb.append(" || Y: " + (int)y);
		return sb.toString();
	}

}
