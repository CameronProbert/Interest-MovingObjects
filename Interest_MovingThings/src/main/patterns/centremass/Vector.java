package main.patterns.centremass;

public class Vector {

	private double x;
	private double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Orb... others){
		double sumX = x;
		double sumY = y;
		for (Orb other : others){
			sumX += other.getVector().getX();
			sumY += other.getVector().getY();
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
