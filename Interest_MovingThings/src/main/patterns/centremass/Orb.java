package main.patterns.centremass;

import java.awt.Graphics;

public class Orb {

	private double centreX;
	private double centreY;
	private double dir;
	private double vel;
	private double magnitude;

	public Orb(double centreX, double centreY) {
		this.centreX = centreX;
		this.centreY = centreY;
		this.dir = Math.random()*360;
		this.vel = Math.random() * 5 + 5;
		this.magnitude = Math.random() * 7.5 + 5;
	}
	
	public void draw(Graphics g){
		
	}

}
