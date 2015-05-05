package main.patterns.linepattern;

import java.awt.Graphics;

import main.patterns.DrawingPattern;

public class LinePattern extends DrawingPattern {

	LineGroup[] lines;

	public LinePattern(double width, double height) {
		super(width, height);
		initialiseLineGroups();
	}

	private void initialiseLineGroups() {
		int numGroups = (int) (Math.random() * 4 + 3);
		lines = new LineGroup[numGroups];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new LineGroup(width, height);
		}
	}

	@Override
	public void step() {
		for (LineGroup line : lines) {
			line.step();
		}
	}

	@Override
	public void draw(Graphics g) {
		for (LineGroup line : lines) {
			line.draw(g);
		}
	}

}
