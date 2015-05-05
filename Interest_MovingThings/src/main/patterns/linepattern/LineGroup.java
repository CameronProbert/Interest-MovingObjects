package main.patterns.linepattern;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import main.patterns.DrawingPattern;

public class LineGroup {

	private static final double NUM_LINES = 40;

	private double width;
	private double height;

	private Queue<Line> lines;
	private Line lastLine;
	private Color baseColour;

	public LineGroup(double width, double height) {
		this.width = width;
		this.height = height;
		int red = (int) (256 * Math.random());
		int blue = (int) (256 * Math.random());
		int green = (int) (256 * Math.random());
		baseColour = new Color(red, blue, green);
		initialiseLines();
	}

	private void initialiseLines() {
		lines = new LinkedList<Line>();
		lines.add(newLine());
	}

	private Line newLine() {
		double x1;
		double y1;
		double x2;
		double y2;
		if (lastLine == null) {
			x1 = Math.random() * (width - 1) + 1;
			y1 = Math.random() * (height - 1) + 1;
			x2 = Math.random() * (width - 1) + 1;
			y2 = Math.random() * (height - 1) + 1;
		} else {
			x1 = lastLine.getX1() + Math.random() * 16 - 8;
			if (x1 < 0)
				x1 = 0;
			if (x1 > width)
				x1 = width;
			y1 = lastLine.getY1() + Math.random() * 16 - 8;
			if (y1 < 0)
				y1 = 0;
			if (y1 > width)
				y1 = width;
			x2 = lastLine.getX2() + Math.random() * 16 - 8;
			if (x2 < 0)
				x2 = 0;
			if (x2 > width)
				x2 = width;
			y2 = lastLine.getY2() + Math.random() * 16 - 8;
			if (y2 < 0)
				y2 = 0;
			if (y2 > width)
				y2 = width;
		}
		Color colour = DrawingPattern.generateRandomMixedColor(baseColour);
		lastLine = new Line(x1, y1, x2, y2, colour);
		return lastLine;
	}

	public void step() {
		lines.add(newLine());
		if (lines.size() > NUM_LINES) {
			lines.poll();
		}
	}

	public void draw(Graphics g) {
		for (Line line : lines) {
			line.draw(g);
		}
	}
}
