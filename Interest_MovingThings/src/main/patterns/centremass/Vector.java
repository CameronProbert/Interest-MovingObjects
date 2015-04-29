package main.patterns.centremass;

public class Vector {

	private double x;
	private double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Vector... others){
		double sumX = x;
		double sumY = y;
		for (Vector other : others){
			sumX += other.getX();
			sumY += other.getY();
		}
		return new Vector(sumX, sumY);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
