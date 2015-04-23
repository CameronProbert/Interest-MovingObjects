package main.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.patterns.DrawingPattern;
import main.patterns.bouncingball.BouncingBallPattern;
import main.patterns.centremass.CentreOfMassPattern;
import main.patterns.movingorbit.MovingOrbitPattern;

public class DrawingPad implements KeyListener {

	private JFrame frame;
	private JPanel panel;
	private DrawingPattern pattern;

	private enum Patterns {
		bouncingBall, centreOfMass, movingOrbit
	}

	private int patternIndex;

	private Loop loop;

	private DrawingPad() {
		patternIndex = 0;
		createFrame();
		createPanel();
		createButtons();
		frame.pack();
		frame.setVisible(true);
		startLoop(new BouncingBallPattern(panel.getWidth(), panel.getHeight()));
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createPanel() {
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				if (pattern != null) {
					java.awt.Image offscreen = createImage(600, 600);
					Graphics offScrG = offscreen.getGraphics();
					drawImage(pattern, offScrG);
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

	private void startLoop(DrawingPattern pattern) {
		if (loop != null) {
			Thread.currentThread().isInterrupted();
		}
		this.pattern = pattern;
		loop = new Loop();
		Thread thread = new Thread(loop);
		thread.run();
	}

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
		restartPattern(newPattern);
	}

	private void restartPattern() {

	}

	private void restartPattern(DrawingPattern pattern) {
		startLoop(pattern);
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
		new DrawingPad();

	}

	private class Loop implements Runnable {

		@Override
		public void run() {
			while (true) {
				pattern.step();
				frame.repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
