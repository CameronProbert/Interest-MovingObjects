package main.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.patterns.DrawingPattern;
import main.patterns.bouncingball.BouncingBallPattern;
import main.patterns.centremass.CentreOfMassPattern;
import main.patterns.linepattern.LinePattern;
import main.patterns.movingorbit.MovingOrbitPattern;

/**
 * A program that displays numerous patterns (using strategies) and uses
 * threading to display these patterns in a GUI. You can use the left, right and
 * down arrow keys to navigate to the previous and next pattern, and restart the
 * current pattern.
 * 
 * @author Cameron Probert
 *
 */
public class DrawingPad implements KeyListener {

	// Time to wait
	private final int sleepTime;

	private JFrame frame;
	private JPanel panel;

	private enum Patterns {
		bouncingBall, centreOfMass, movingOrbit, linePattern
	}

	private int patternIndex;

	private Loop loop;

	/**
	 * Constructs the drawing pad
	 * 
	 * @param sleepTime
	 */
	private DrawingPad(int sleepTime) {
		patternIndex = 0;
		this.sleepTime = sleepTime;
		createFrame();
		createPanel();
		createButtons();
		frame.pack();
		frame.setVisible(true);
		startLoop(new BouncingBallPattern(panel.getWidth(), panel.getHeight()));
	}

	/**
	 * Initialises the frame
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
	}

	/**
	 * Initialises the graphics panel
	 */
	private void createPanel() {
		panel = new JPanel() {

			// Automatically generated UID
			private static final long serialVersionUID = 8985280854150377230L;

			@Override
			public void paintComponent(Graphics g) {
				if (loop.getInternalPattern() != null) {
					java.awt.Image offscreen = createImage(600, 600);
					Graphics offScrG = offscreen.getGraphics();
					drawImage(loop.getInternalPattern(), offScrG);
					g.drawImage(offscreen, 0, 0, this);
				}
			}

			public void drawImage(DrawingPattern pattern, Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				pattern.draw(g);
			}
		};
		panel.setBackground(Color.black);
		panel.setPreferredSize(new Dimension(600, 600));
		frame.add(panel);
	}

	private void createButtons() {
		// TODO Auto-generated method stub

	}

	/**
	 * Starts the loop
	 * 
	 * @param pattern
	 */
	private void startLoop(DrawingPattern pattern) {
		loop = new Loop(pattern);
		Thread thread = new Thread(loop);
		thread.start();
	}

	/**
	 * Stops the current pattern and renders the previous one
	 */
	private void previousPattern() {
		DrawingPattern newPattern = null;
		patternIndex--;
		if (patternIndex < 0) {
			patternIndex = Patterns.values().length - 1;
		}
		switch (Patterns.values()[patternIndex]) {
		case bouncingBall:
			newPattern = new BouncingBallPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case centreOfMass:
			newPattern = new CentreOfMassPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case movingOrbit:
			newPattern = new MovingOrbitPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case linePattern:
			newPattern = new LinePattern(panel.getWidth(), panel.getHeight());
			break;
		default:
			break;

		}
		restartPattern(newPattern);
	}

	private void nextPattern() {
		DrawingPattern newPattern = null;
		patternIndex++;
		if (patternIndex > Patterns.values().length - 1) {
			patternIndex = 0;
		}
		switch (Patterns.values()[patternIndex]) {
		case bouncingBall:
			newPattern = new BouncingBallPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case centreOfMass:
			newPattern = new CentreOfMassPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case movingOrbit:
			newPattern = new MovingOrbitPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case linePattern:
			newPattern = new LinePattern(panel.getWidth(), panel.getHeight());
			break;
		default:
			break;

		}
		restartPattern(newPattern);
	}

	private void restartPattern() {
		DrawingPattern newPattern = null;
		switch (Patterns.values()[patternIndex]) {
		case bouncingBall:
			newPattern = new BouncingBallPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case centreOfMass:
			newPattern = new CentreOfMassPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case movingOrbit:
			newPattern = new MovingOrbitPattern(panel.getWidth(),
					panel.getHeight());
			break;
		case linePattern:
			newPattern = new LinePattern(panel.getWidth(), panel.getHeight());
			break;
		default:
			break;
		}
		loop.setNewPattern(newPattern);
	}

	private void restartPattern(DrawingPattern pattern) {
		loop.setNewPattern(pattern);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			restartPattern();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			previousPattern();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			nextPattern();
		}
	}

	// //////////////////////////MAIN/////////////////////////////////
	public static void main(String[] args) {
		if (args.length == 1) {
			int sleepTime = Integer.parseInt(args[0]);
			new DrawingPad(sleepTime);
		} else {
			new DrawingPad(20);
		}

	}

	private class Loop implements Runnable {

		private DrawingPattern internalPattern;
		private DrawingPattern newPattern;

		private Loop(DrawingPattern pattern) {
			super();
			internalPattern = pattern;
		}

		private void setNewPattern(DrawingPattern pattern) {
			newPattern = pattern;
		}

		private DrawingPattern getInternalPattern() {
			return internalPattern;
		}

		@Override
		public void run() {
			while (true) {
				if (newPattern != null) {
					internalPattern = newPattern;
					newPattern = null;
				}
				internalPattern.step();
				frame.repaint();
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
