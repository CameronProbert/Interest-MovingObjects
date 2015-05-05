package main.patterns.linepattern;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import main.patterns.DrawingPattern;

public class LinePattern extends DrawingPattern {

	private static final double FLIP_FLOP_THRESH = 0.01;
	private Queue<Line> lines;
	private Line lastLine;

	private boolean firstPointActive = false;

	public LinePattern(double width, double height) {
		super(width, height);
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
			x1 = lastLine.getX1();
			y1 = lastLine.getY1();
			x2 = lastLine.getX2();
			y2 = lastLine.getY2();
			if (firstPointActive) {
				x1 = lastLine.getX1() + Math.random() * 8 - 4;
				if (x1 < 0)
					x1 = 0;
				if (x1 > width)
					x1 = width;
				y1 = lastLine.getY1() + Math.random() * 8 - 4;
				if (y1 < 0)
					y1 = 0;
				if (y1 > width)
					y1 = width;
			} else {
				x2 = lastLine.getX2() + Math.random() * 8 - 4;
				if (x2 < 0)
					x2 = 0;
				if (x2 > width)
					x2 = width;
				y2 = lastLine.getY2() + Math.random() * 8 - 4;
				if (y2 < 0)
					y2 = 0;
				if (y2 > width)
					y2 = width;
			}
		}
		Color colour = DrawingPattern.generateRandomMixedColor(Color.WHITE);
		lastLine = new Line(x1, y1, x2, y2, colour);
		return lastLine;
	}

	@Override
	public void step() {
		if (lines.size() > 10) {
			lines.poll();
		}
		lines.add(newLine());
		if (Math.random() < FLIP_FLOP_THRESH){
			firstPointActive = !firstPointActive;
		}
	}

	@Override
	public void draw(Graphics g) {
		for (Line line : lines) {
			line.draw(g);
		}
	}

}
